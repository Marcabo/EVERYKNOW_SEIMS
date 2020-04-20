package com.herion.everyknow.seims.utils;

import com.auth0.jwt.JWT;
import com.herion.everyknow.common.exception.EKnowException;
import com.herion.everyknow.seims.dao.entity.SysUser;
import com.herion.everyknow.seims.dao.entity.SysUserRole;
import com.herion.everyknow.seims.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description 用户工具
 * @auther wubo25320
 * @create 2020-04-20 17:22
 */
@Component
public class UserUtil {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取当前登录用户
     * @return
     */
    public SysUser getUser() {
        String token = SecurityUtils.getSubject().getPrincipal().toString();
        // 解密获得 Account
        String username = JWTUtil.getUsername(token);
        SysUser user = sysUserService.getUser(username);
        if (user == null) {
            throw new EKnowException("该账号不存在");
        }
        return user;
    }

    /**
     * 获取当前用户id
     * @return
     */
    public Integer getUserId() {
        return getUser().getId();
    }

    /**
     * 获取当前登录用户token
     * @return
     */
    public String getToken() {
        return SecurityUtils.getSubject().getPrincipal().toString();
    }

    /**
     * 获取当前登录用户username
     * @return
     */
    public String getUserName() {
        String token = SecurityUtils.getSubject().getPrincipal().toString();
        // 解密获得Account
        return JWTUtil.getUsername(token);
    }

}
