package com.herion.everyknow.seims.facade.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description 修改密码请求
 * @auther wubo25320
 * @create 2020-04-23 14:21
 */
@Getter
@Setter
public class ChangePasswordRequest {

    /**
     * SysUser 表的主键
     */
    private Integer userId;

    private String oldPassword;

    private String newPassword;
}
