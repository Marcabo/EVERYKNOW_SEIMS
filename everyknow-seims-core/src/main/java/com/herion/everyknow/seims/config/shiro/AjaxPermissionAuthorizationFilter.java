package com.herion.everyknow.seims.config.shiro;


import cn.hutool.json.JSONUtil;
import com.herion.everyknow.web.enums.EnumResponseType;
import com.herion.everyknow.web.response.EKnowResponse;
import com.herion.everyknow.web.utils.ResultUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Description 对没有登录的请求进行拦截, 全部返回 json 信息, 覆盖掉shiro原本的 跳转 login.jsp的拦截方式
 * <br />
 * 为什么要继承 FormAuthenticationFilter 这是 authc 过滤器的 默认 Filter
 * @auther wubo25320
 * @create 2020-04-16 11:00
 */
// 暂时废弃
public class AjaxPermissionAuthorizationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        对请求进行处理,现在不需要
//        HttpServletRequest httpRequesst = (HttpServletRequest) request;
//        BufferedReader br = null;
//        br = httpRequesst.getReader();
//        String line, wholeStr = "";
//        while ((line = br.readLine()) != null) {
//            wholeStr += line;
//        }
//        JSONUtil.parseObj(wholeStr);

        EKnowResponse<Object> failureResponse = ResultUtils.getFailureResponse("401", "登录已过期,请重新登录", EnumResponseType.NO_LOGIN, null);
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
        return false;
    }
}
