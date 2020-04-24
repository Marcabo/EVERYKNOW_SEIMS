package com.herion.everyknow.seims.facade.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description 用户个人信息 请求
 * @auther wubo25320
 * @create 2020-04-23 14:36
 */
@Getter
@Setter
public class UserProfileRequest {
    /**
     * 主键
     */
    @ApiModelProperty(value = "SysUser 表 主键")
    private Integer id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty("角色名称")
    private String rolename;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;
}
