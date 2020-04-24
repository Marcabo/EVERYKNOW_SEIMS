package com.herion.everyknow.seims.facade.response;

import com.herion.everyknow.seims.dao.entity.SysPermission;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Description 用户信息返回 (不是 UserProfile 的表)
 * @auther wubo25320
 * @create 2020-04-21 16:25
 */
@Setter
@Getter
@ToString
public class UserInfoResponse {
    /**
     * 主键
     */
    @ApiModelProperty(value = "(sys_user 表 主键")
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
     * 角色名称
     */
    @ApiModelProperty(value = "角色")
    private String roleName;

    @ApiModelProperty(value = "权限列表")
    private List<SysPermission> permissions;

    /**
     * 头像链接
     */
    private String avatar;
}
