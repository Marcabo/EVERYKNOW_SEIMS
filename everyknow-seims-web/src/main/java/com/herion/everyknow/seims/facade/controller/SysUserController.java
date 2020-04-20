package com.herion.everyknow.seims.facade.controller;

import com.herion.everyknow.common.bean.Constant;
import com.herion.everyknow.common.exception.EKnowException;
import com.herion.everyknow.seims.config.EknowConfig;
import com.herion.everyknow.seims.config.shiro.JWTToken;
import com.herion.everyknow.seims.dao.entity.SysUser;
import com.herion.everyknow.seims.service.SysUserService;
import com.herion.everyknow.seims.utils.AesCipherUtil;
import com.herion.everyknow.seims.utils.JWTUtil;
import com.herion.everyknow.seims.utils.JedisUtil;
import com.herion.everyknow.seims.utils.SerializableUtil;
import com.herion.everyknow.web.request.http.CommonHttpRequest;
import com.herion.everyknow.web.response.EKnowResponse;
import com.herion.everyknow.web.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description 用户 Controller
 * @auther wubo25320
 * @create 2020-04-16 10:17
 */
@Api
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

//    @Autowired
//    private EknowConfig eknowConfig;

//    @RequestMapping("/login")
//    @ApiOperation("用户登录")
//    public EKnowResponse login(@RequestBody CommonHttpRequest<SysUser> request) {
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(request.getRequest().getUsername(), request.getRequest().getPassword());
//        subject.login(usernamePasswordToken);
//        return ResultUtils.getSuccessResponse(true);
//
//    }

    @RequestMapping("/login")
    @ApiOperation("用户登录")
    public EKnowResponse login(@RequestBody CommonHttpRequest<SysUser> request, HttpServletResponse response) {
        // 查询数据库中的账号信息
        SysUser userTemp = sysUserService.getUser(request.getRequest().getUsername());
        if (userTemp == null) {
            throw new EKnowException("该账号不存在(The account does not exist.)");
        }
        // 密码进行解密
        // 数据库中的密码使用 账号+密码进行加密的.所以需要解密
//        String dePassword = AesCipherUtil.deCrypto(userTemp.getPassword());
//        if (!dePassword.equals(request.getRequest().getUsername()+request.getRequest().getPassword())) {
//            throw new EKnowException("账号或密码错误(Account or Password Error.)");
//        }
        String dePassword = userTemp.getPassword();
        if (!dePassword.equals(request.getRequest().getPassword())) {
            throw new EKnowException("账号或密码错误(Account or Password Error.)");
        }
        // 清除 Jedis 中可能存在的权限信息缓存
        if (JedisUtil.exists(Constant.PREFIX_SHIRO_CACHE + request.getRequest().getUsername())) {
            JedisUtil.delKey(Constant.PREFIX_SHIRO_CACHE + request.getRequest().getUsername());
        }
        // 设置RefreshToken, 时间戳为当前时间戳.直接设置即可(不用先删后设,会覆盖已有的RefreshToken)
        String currentTimeMillis = String.valueOf(System.currentTimeMillis());
        JedisUtil.setObject(Constant.PREFIX_SHIRO_REFRESH_TOKEN + request.getRequest().getUsername(), currentTimeMillis, (int)EknowConfig.getRefreshTokenExpireTime());
        // 在 Header 中的Authorization 设置AccessToken.时间戳为当前时间
        String token = JWTUtil.sign(request.getRequest().getUsername(), String.valueOf(System.currentTimeMillis()));

//        // 这里不能做 login. 因为此时
//        Subject subject = SecurityUtils.getSubject();
//        JWTToken jwtToken = new JWTToken(token);
//        subject.login(jwtToken);

        response.setHeader("Authorization", token);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");

        return ResultUtils.getSuccessResponse(true);

    }
}
