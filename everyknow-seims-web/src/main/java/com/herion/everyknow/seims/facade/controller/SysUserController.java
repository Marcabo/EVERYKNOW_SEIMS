package com.herion.everyknow.seims.facade.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.herion.everyknow.common.bean.Constant;
import com.herion.everyknow.common.exception.EKnowException;
import com.herion.everyknow.seims.config.EknowConfig;
import com.herion.everyknow.seims.config.shiro.JWTToken;
import com.herion.everyknow.seims.dao.entity.*;
import com.herion.everyknow.seims.facade.request.ChangePasswordRequest;
import com.herion.everyknow.seims.facade.request.SysUserRequest;
import com.herion.everyknow.seims.facade.request.UserProfileRequest;
import com.herion.everyknow.seims.facade.response.UserInfoResponse;
import com.herion.everyknow.seims.facade.response.UserProfileResponse;
import com.herion.everyknow.seims.facade.utils.PageUtil;
import com.herion.everyknow.seims.service.*;
import com.herion.everyknow.seims.utils.*;
import com.herion.everyknow.web.enums.EnumResponseType;
import com.herion.everyknow.web.request.http.CommonHttpPageRequest;
import com.herion.everyknow.web.request.http.CommonHttpRequest;
import com.herion.everyknow.web.response.EKnowPageResponse;
import com.herion.everyknow.web.response.EKnowResponse;
import com.herion.everyknow.web.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserUtil userUtil;

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
            throw new EKnowException(EnumResponseType.ERR_TOKEN.getHttp(),"该账号不存在(The account does not exist.)");
        }
        // 密码进行解密
        // 数据库中的密码使用 账号+密码进行加密的.所以需要解密
//        String dePassword = AesCipherUtil.deCrypto(userTemp.getPassword());
//        if (!dePassword.equals(request.getRequest().getUsername()+request.getRequest().getPassword())) {
//            throw new EKnowException("账号或密码错误(Account or Password Error.)");
//        }
        String dePassword = userTemp.getPassword();
        if (!dePassword.equals(request.getRequest().getPassword())) {
            throw new EKnowException(EnumResponseType.ERR_TOKEN.getHttp(), "账号或密码错误(Account or Password Error.)");
        }
        // 清除 Jedis 中可能存在的权限信息缓存
        if (JedisUtil.exists(Constant.PREFIX_SHIRO_CACHE + request.getRequest().getUsername())) {
            JedisUtil.delKey(Constant.PREFIX_SHIRO_CACHE + request.getRequest().getUsername());
        }
        // 设置RefreshToken, 时间戳为当前时间戳.直接设置即可(不用先删后设,会覆盖已有的RefreshToken)
        String currentTimeMillis = String.valueOf(System.currentTimeMillis());
        JedisUtil.setObject(Constant.PREFIX_SHIRO_REFRESH_TOKEN + request.getRequest().getUsername(), currentTimeMillis, (int)EknowConfig.getRefreshTokenExpireTime());
        // 在 Header 中的Authorization 设置AccessToken.时间戳为当前时间
        String token = JWTUtil.sign(request.getRequest().getUsername(), currentTimeMillis);

//        // 这里不能做 login. 因为此时
//        Subject subject = SecurityUtils.getSubject();
//        JWTToken jwtToken = new JWTToken(token);
//        subject.login(jwtToken);

        response.setHeader("Authorization", token);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");

        return ResultUtils.getSuccessResponse(token);
    }

    @RequestMapping(value = "/currentUserInfo", method = RequestMethod.POST)
    @ApiOperation("获取当前登录用户信息(用于前端鉴权)")
    public EKnowResponse currentUserInfo() {
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        SysUser currentUser = userUtil.getUser();
        userInfoResponse.setId(currentUser.getId());
        userInfoResponse.setNickname(currentUser.getNickname());
        userInfoResponse.setUsername(currentUser.getUsername());
        userInfoResponse.setRoleName(userUtil.getCurrentRole().getRoleName());
        userInfoResponse.setPermissions(userUtil.getCurrentPermission());
        userInfoResponse.setAvatar("https://i.loli.net/2020/04/23/xEycWVdvZbDjpo1.gif");
        return ResultUtils.getSuccessResponse(userInfoResponse);
    }

    @RequestMapping(value = "/getUserById", method = RequestMethod.POST)
    @ApiOperation("获取指定用户信息(根据 id)")
    public EKnowResponse getUser(@RequestBody CommonHttpRequest<SysUserRequest> request) {
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        SysUser user = sysUserService.getUserById(request.getRequest().getId());
        SysRole role = sysRoleService.queryById(sysUserRoleService.queryByUserId(user.getId()).getRoleId());
        List<SysRolePermission> sysRolePermissionList = role.getId() != null ? sysRolePermissionService.queryByRoleId(role.getId()) : Collections.EMPTY_LIST;
        List<SysPermission> permissionList = !sysRolePermissionList.isEmpty() ? sysRolePermissionList.stream().map(sysRolePermission -> sysPermissionService.queryById(sysRolePermission.getPermissionId())).collect(Collectors.toList()) : Collections.EMPTY_LIST;
        userInfoResponse.setId(user.getId());
        userInfoResponse.setNickname(user.getNickname());
        userInfoResponse.setUsername(user.getUsername());
        userInfoResponse.setRoleName(role.getRoleName());
        userInfoResponse.setPermissions(permissionList);

        return ResultUtils.getSuccessResponse(userInfoResponse);
    }

    @RequestMapping(value = "/userProfile", method = RequestMethod.POST)
    @ApiOperation("获取当前登录用户信息(用于个人中心)")
    public EKnowResponse UserProfile() {
        SysUser currentUser = userUtil.getUser();

        UserProfileResponse userProfileResponse = new UserProfileResponse();
        userProfileResponse.setId(currentUser.getId());
        userProfileResponse.setUsername(currentUser.getUsername());
        userProfileResponse.setNickname(currentUser.getNickname());

        UserProfile userProfile = userProfileService.queryByUserId(currentUser.getId());
        if (userProfile != null && userProfile.getId() != null) {
            userProfileResponse.setAvatar(userProfile.getAvatar());
            userProfileResponse.setPhone(userProfile.getPhone());
            userProfileResponse.setEmail(userProfile.getEmail());
        }

        return ResultUtils.getSuccessResponse(userProfileResponse);
    }

    @RequestMapping(value = "/changeProfile", method = RequestMethod.POST)
    @ApiOperation("修改个人信息")
    public EKnowResponse changeProfile(@RequestBody CommonHttpRequest<UserProfileRequest> request) {
        Integer userId = request.getRequest().getId();
        SysUser currentUser = userUtil.getUser();
        if (!userId.equals(currentUser.getId())) {
            // 如果修改的不是当前用户
            throw new EKnowException("你不能修改其他用户的信息");
        }
        if (StrUtil.isNotBlank(request.getRequest().getNickname())) {
            sysUserService.updateNickName(userId, request.getRequest().getNickname());
        }

        UserProfile oldUserProfile = userProfileService.queryByUserId(userId);

        if (oldUserProfile == null) {
            // 如果UserProfile 中不存在, 说明是新增
            oldUserProfile = new UserProfile();
        }

        oldUserProfile.setUserId(userId);
        if (StrUtil.isNotBlank(request.getRequest().getAvatar())) {
            oldUserProfile.setAvatar(request.getRequest().getAvatar());
        }
        if (StrUtil.isNotBlank(request.getRequest().getPhone())) {
            oldUserProfile.setPhone(request.getRequest().getPhone());
        }
        if (StrUtil.isNotBlank(request.getRequest().getEmail())) {
            oldUserProfile.setEmail(request.getRequest().getEmail());
        }

        if (oldUserProfile.getId() == null) {
            userProfileService.insert(oldUserProfile);
        } else {
            userProfileService.updateById(oldUserProfile);
        }

        return ResultUtils.getSuccessResponse(true);
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    @ApiOperation("修改密码")
    public EKnowResponse changePassword(@RequestBody CommonHttpRequest<ChangePasswordRequest> request) {
        Integer userId = request.getRequest().getUserId();
        SysUser currentUser = userUtil.getUser();
        if (!userId.equals(currentUser.getId())) {
            // 如果修改的不是当前用户
            throw new EKnowException("你不能修改其他用户的密码");
        }
        if (!currentUser.getPassword().equals(request.getRequest().getOldPassword())) {
            // 如果修改的原密码不正确
            throw new EKnowException("原密码不正确!");
        }
        sysUserService.changePasswordById(currentUser.getId(), request.getRequest().getNewPassword());
        return ResultUtils.getSuccessResponse(true);
    }


    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ApiOperation("分页获取用户(分角色)")
    public EKnowPageResponse page(@RequestBody CommonHttpPageRequest<UserProfileRequest> request) {
        // 根据 用户角色(rolename), 条件(用户名, 用户昵称), 分页请求
        String rolename = request.getRequest().getRolename();
        if (StrUtil.isBlank(rolename)) {
            throw new EKnowException("rolename 不能为空");
        }
        SysRole sysRole = sysRoleService.queryByRoleName(rolename);

        SysUser sysUser = new SysUser();
        sysUser.setUsername(request.getRequest().getUsername());
        sysUser.setNickname(request.getRequest().getNickname());
        IPage iPage = sysUserService.queryPageAndRoleId(PageUtil.eKnow2Plus(request), sysUser, sysRole.getId());
        // List<SysUser> 转 List<UserProfile>
        List<UserProfileResponse> collect = (List<UserProfileResponse>) iPage.getRecords().stream().map(record -> {
            SysUser fromRecord = (SysUser) record;
            UserProfile userProfile = userProfileService.queryByUserId(fromRecord.getId());
            UserProfileResponse userProfileResponse = new UserProfileResponse();
            userProfileResponse.setId(fromRecord.getId());
            userProfileResponse.setUsername(fromRecord.getUsername());
            userProfileResponse.setNickname(fromRecord.getNickname());
            if (userProfile != null) {
                userProfileResponse.setPhone(userProfile.getPhone());
                userProfileResponse.setEmail(userProfile.getEmail());
            }
            return userProfileResponse;
        }).collect(Collectors.toList());
        iPage.setRecords(collect);
        return PageUtil.plus2EKnow(iPage);
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ApiOperation("添加用户")
    public EKnowResponse addUser(@RequestBody CommonHttpRequest<UserProfileRequest> request) {
        SysUser addSysUser = new SysUser();
        addSysUser.setUsername(request.getRequest().getUsername());
        addSysUser.setNickname(request.getRequest().getNickname());
        addSysUser.setPassword("123456");
        SysUser insertSysUser = sysUserService.insert(addSysUser);

        SysRole sysRole = sysRoleService.queryByRoleName(request.getRequest().getRolename());
        SysUserRole addUserRole = new SysUserRole();
        addUserRole.setUserId(insertSysUser.getId());
        addUserRole.setRoleId(sysRole.getId());
        sysUserRoleService.insert(addUserRole);

        UserProfile addUserProfile = new UserProfile();
        addUserProfile.setUserId(insertSysUser.getId());
        addUserProfile.setPhone(request.getRequest().getPhone());
        addUserProfile.setEmail(request.getRequest().getEmail());
        userProfileService.insert(addUserProfile);

        return ResultUtils.getSuccessResponse(true);
    }


    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ApiOperation("修改用户")
    public EKnowResponse updateUser(@RequestBody CommonHttpRequest<UserProfileRequest> request) {
        Integer userId = request.getRequest().getId();

        if (StrUtil.isNotBlank(request.getRequest().getNickname())) {
            sysUserService.updateNickName(userId, request.getRequest().getNickname());
        }

        UserProfile oldUserProfile = userProfileService.queryByUserId(userId);

        if (oldUserProfile == null) {
            // 如果UserProfile 中不存在, 说明是新增
            oldUserProfile = new UserProfile();
        }

        oldUserProfile.setUserId(userId);
        if (StrUtil.isNotBlank(request.getRequest().getAvatar())) {
            oldUserProfile.setAvatar(request.getRequest().getAvatar());
        }
        if (StrUtil.isNotBlank(request.getRequest().getPhone())) {
            oldUserProfile.setPhone(request.getRequest().getPhone());
        }
        if (StrUtil.isNotBlank(request.getRequest().getEmail())) {
            oldUserProfile.setEmail(request.getRequest().getEmail());
        }

        if (oldUserProfile.getId() == null) {
            userProfileService.insert(oldUserProfile);
        } else {
            userProfileService.updateById(oldUserProfile);
        }

        return ResultUtils.getSuccessResponse(true);
    }

    @RequestMapping(value = "/resetUserPassword", method = RequestMethod.POST)
    @ApiOperation("重置用户密码(根据用户id)")
    public EKnowResponse resetUserPassword(@RequestBody CommonHttpRequest<ChangePasswordRequest> request) {
        Integer userId = request.getRequest().getUserId();
        SysUser sysUser = sysUserService.getUserById(userId);

        sysUserService.changePasswordById(sysUser.getId(), "123456");
        return ResultUtils.getSuccessResponse(true);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    @ApiOperation("根据用户id删除用户")
    public EKnowResponse deleteUser(@RequestBody CommonHttpRequest<SysUserRequest> request) {
        sysUserService.deleteById(request.getRequest().getId());
        sysUserRoleService.deleteByUserId(request.getRequest().getId());
        userProfileService.deleteByUserId(request.getRequest().getId());

        return ResultUtils.getSuccessResponse(true);
    }
}
