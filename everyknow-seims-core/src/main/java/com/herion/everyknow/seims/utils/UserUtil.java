package com.herion.everyknow.seims.utils;

import com.auth0.jwt.JWT;
import com.herion.everyknow.common.exception.EKnowException;
import com.herion.everyknow.seims.dao.entity.*;
import com.herion.everyknow.seims.service.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 用户工具
 * @auther wubo25320
 * @create 2020-04-20 17:22
 */
@Component
public class UserUtil {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    @Autowired
    private SysPermissionService sysPermissionService;

    /**
     * 当前用户
     */
    private SysUser currentUser;

    /**
     * 当前用户 角色
     */
    private SysRole currentRole;

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
        currentUser = user;
        return user;
    }

    /**
     * 获取当前用户id
     * @return
     */
    public Integer getUserId() {
        return currentUser.getId();
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

    /**
     * 获取当前用户 用户角色
     * @return
     */
    public SysUserRole getUserRole() {
        return sysUserRoleService.queryByUserId(currentUser.getId());
    }

    /**
     * 获取当前用户 角色
     * @return
     */
    public SysRole getCurrentRole() {
        SysUserRole userRole = getUserRole();
        currentRole = sysRoleService.queryById(userRole.getRoleId());
        return currentRole;
    }

    /**
     * 获取当前用户 角色权限
     * @return
     */
    public List<SysRolePermission> getRolePermission() {
        SysUserRole userRole = getUserRole();
        return sysRolePermissionService.queryByRoleId(userRole.getRoleId());
    }

    /**
     * 获取当前用户 权限
     * @return
     */
    public List<SysPermission> getCurrentPermission() {
        List<SysRolePermission> rolePermissionList = getRolePermission();
        return rolePermissionList.stream().map(rolePermission -> sysPermissionService.queryById(rolePermission.getPermissionId())).collect(Collectors.toList());
    }
}
