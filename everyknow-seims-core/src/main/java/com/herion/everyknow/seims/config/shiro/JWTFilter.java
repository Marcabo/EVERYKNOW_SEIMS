package com.herion.everyknow.seims.config.shiro;

import cn.hutool.json.JSONUtil;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.herion.everyknow.common.bean.Constant;
import com.herion.everyknow.seims.config.EknowConfig;
import com.herion.everyknow.seims.utils.JWTUtil;
import com.herion.everyknow.seims.utils.JedisUtil;
import com.herion.everyknow.web.enums.EnumResponseType;
import com.herion.everyknow.web.response.EKnowResponse;
import com.herion.everyknow.web.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Description JWT 过滤
 * @auther wubo25320
 * @create 2020-04-17 17:07
 */
// Filter 中执行的流程 preHandle -> isAccessAllowed -> isLoginAttempt -> executeLogin
@Slf4j
public class JWTFilter extends BasicHttpAuthenticationFilter {

//    private static Boolean mustLoginFlag;
//
//    public Boolean getMustLoginFlag() {
//        return mustLoginFlag;
//    }
//
//    @Value("${eknow.mustLogin}")
//    public void setMustLoginFlag(Boolean mustLoginFlag) {
//        this.mustLoginFlag = mustLoginFlag;
//    }

    // 在这里可以对跨域提供支持
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        return super.preHandle(request, response);
    }

    /**
     * 这里我们详细说明下为什么最终返回的都是true，即允许访问
     * 例如我们提供一个地址 GET /article
     * 登入用户和游客看到的内容是不同的
     * 如果在这里返回了false，请求会被直接拦截，用户看不到任何东西
     * 所以我们在这里返回true，Controller中可以通过 subject.isAuthenticated()(认证) 来判断用户是否登入
     * 如果有些资源只有登入用户才能访问，我们只需要在方法上面加上 @RequiresAuthentication 注解即可
     * 但是这样做有一个缺点，就是不能够对GET,POST等请求进行分别过滤鉴权(因为我们重写了官方的方法)，但实际上对应用影响不大(如果需要看 父类的这个方法怎么实现的就行)
     *  这里返回 true. 就是允许访问; 返回 false. 就是拒绝访问.会到 onAccessDenied中执行逻辑
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        // 查看当前 Header 中是否携带 Authorization 属性(Token), 有的话就进行登录认证授权
        if (this.isLoginAttempt(request, response)) {
            try {
                // 进行 Shiro 的登录 UserRealm
                this.executeLogin(request, response);
            } catch (Exception e) {
                // 出现认证异常, 传递错误信息 msg
                String msg = e.getMessage();
                // 获取应用异常 (该 Cause是导致抛出 throwable(异常)的 throwable(异常))
                Throwable throwable = e.getCause();
                EKnowResponse<Object> failureResponse = ResultUtils.getFailureResponse("401", msg, EnumResponseType.ERR_TOKEN, null);;
                if (throwable instanceof SignatureVerificationException) {
                    // 该异常为 JWT 异常, AccessToken 认证失败(Token 或 秘钥不对)
                    failureResponse = ResultUtils.getFailureResponse("401", "Token 或 秘钥不正确", EnumResponseType.ERR_TOKEN, null);
                } else if (throwable instanceof TokenExpiredException) {
                    // 该异常为 JWT 的 AccessToken 已过期, 判断RefreshToken 未过期就进行 AccessToken 刷新
                    if (this.refreshToken(request, response)) {
                        return true;
                    } else {
                        failureResponse = ResultUtils.getFailureResponse("401", "登录已过期,请重新登录", EnumResponseType.ERR_TOKEN, null);
                    }
                } else {
                    // 应用异常
                    if (throwable != null) {
                        // 获取应用异常
                        failureResponse = ResultUtils.getFailureResponse("401", throwable.getMessage(), EnumResponseType.ERR_TOKEN, null);
                    }
                }
                // Token认证失败直接返回 Response信息
                this.response401(response, failureResponse);
                return false;
            }
        } else {
            // 没有携带 Token
            HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
            // 获取当前请求类型
            String requestMethod = httpServletRequest.getMethod();
            // 获取当前请求URI
            String requestURI = httpServletRequest.getRequestURI();
            log.info("当前请求 {} Authorization属性(Token)为空, 请求类型 {}",requestURI, requestMethod);
            // mustLoginFlag = true 开启任何请求都必须登录才可访问
            final Boolean mustLoginFlag = EknowConfig.getMustLogin();
            if (mustLoginFlag) {
                EKnowResponse<Object> failureResponse = ResultUtils.getFailureResponse("401", "登录已过期,请重新登录", EnumResponseType.NO_LOGIN, null);
                this.response401(response, failureResponse);
                return false;
            }
        }
        return true;
    }

    /**
     * 检测 Header 中是否包含 Authorization 字段, 有就进行Token登录认证授权(这里不是检测是否为
     *      登录请求.而是判断 Authorization 字段是否存在. 如果存在, 就会在 isAccessAllowed 方法中
     *      判断 token的正确性; 如果不存在 就不会验证 token)
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        // 拿到当前 Header 中 Authorization 的 AccessToken(Shiro 中 getAuthzHeader方法已实现)
        String token = this.getAuthzHeader(request);
        return token != null;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        // 拿到当前Header中Authorization的AccessToken(Shiro中getAuthzHeader方法已经实现)
        JWTToken token = new JWTToken(this.getAuthzHeader(request));
        // 提交给UserRealm进行认证，如果错误他会抛出异常并被捕获
        this.getSubject(request, response).login(token);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * 当执行这个代码的时候.说明 isAccessAllowed 返回的是 false
     *
     * 这里我们详细说明下为什么重写
     * 可以对比父类方法, 只是将executeLogin 方法调用除去了
     * 如果没有去除将会循环调用doGetAuthenticationInfo 方法
     *
     * @return 如果返回true表示需要继续处理;如果返回false表示该拦截器实例已经处理了,将直接返回即可。
     **/
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        this.sendChallenge(request, response);
        return false;
    }

    /**
     * 处理非法请求
     * @param response
     * @param msg
     */
    private void response401(ServletResponse response, Object msg) {
        EKnowResponse<Object> failureResponse = (EKnowResponse<Object>) msg;
        PrintWriter out = null;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        try {
            httpResponse.setCharacterEncoding("UTF-8");
            httpResponse.setContentType("application/json");
            out =httpResponse.getWriter();
            out.println(JSONUtil.parseObj(failureResponse).setDateFormat("yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
        }
    }


    /**
     * 此处为AccessToken刷新, 判断RefreshToken是否过期, 未过期就返回新的 AccessToken且继续访问
     * @param request
     * @param response
     * @return
     */
    // TODO
    private boolean refreshToken(ServletRequest request, ServletResponse response) {
        // 拿到当前 Header 中 Authorization 的 AccessToken(Shiro 中的 getAuthzHeader方法已实现)
        String token = this.getAuthzHeader(request);
        // 获取 Token的账号信息
        String username = JWTUtil.getUsername(token);
        // 判断Redis 中 RefreshToken是否存在
        if (JedisUtil.exists(Constant.PREFIX_SHIRO_REFRESH_TOKEN + username)) {
            // Redis中 RefreshToken还存在, 获取RefreshToken的时间戳
            String currentTimeMillisRedis = JedisUtil.getObject(Constant.PREFIX_SHIRO_REFRESH_TOKEN + username).toString();
            // 获取当前AccessToken中的时间戳, 与 RefreshToken的时间戳作对比, 如果当前时间戳一致, 进行 AccessToken刷新
            if (JWTUtil.getCurrentTimeMillis(token).equals(currentTimeMillisRedis)) {
                // 获取当前最新时间戳
                String currentTimeMillis = String.valueOf(System.currentTimeMillis());
                // 设置RefreshToken中的时间戳为当前最新时间戳, 且刷新过期时间重新设置为 30 分钟过期(配置文件可配置 RefreshTokenExpireTime属性)
                JedisUtil.setObject(Constant.PREFIX_SHIRO_REFRESH_TOKEN + username, currentTimeMillis, (int)EknowConfig.getRefreshTokenExpireTime());
                System.out.println(EknowConfig.getRefreshTokenExpireTime());
                // 刷新 AccessToken.设置时间戳为当前最新时间戳
                token = JWTUtil.sign(username, currentTimeMillis);
                // 将刷新的AccessToken再次进行Shiro的登录
                JWTToken jwtToken = new JWTToken(token);
                // 提交给 UserRealm 进行认证, 如果错误会抛出异常被捕获
                this.getSubject(request, response).login(jwtToken);
                // 最后将刷新的AccessToken存放在Response的 Header中的 Authorization字段返回
                HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
                httpServletResponse.setHeader("Authorization", token);
                httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
                return true;
            }
        }
        return false;
    }
}
