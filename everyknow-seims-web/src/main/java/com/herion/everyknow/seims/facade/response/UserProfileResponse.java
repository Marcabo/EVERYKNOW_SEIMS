package com.herion.everyknow.seims.facade.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description 用户个人中心信息返回(不是 UserProfile 表的返回)
 * @auther wubo25320
 * @create 2020-04-23 13:09
 */
@Getter
@Setter
public class UserProfileResponse {
    /**
     * 主键
     */
    @ApiModelProperty(value = "sys_user 表 主键")
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
