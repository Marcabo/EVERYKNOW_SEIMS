package com.herion.everyknow.seims.service;

import com.herion.everyknow.seims.dao.entity.SysUser;
    /**
  *@Description     ${description}
  *@auther          wubo25320
  *@create          2020-03-24 11:05
  */
public interface SysUserService{

        /**
         * 根据 用户名和密码 获取用户
         * @param username
         * @param password
         * @return
         */
    SysUser getUser(String username, String password);

        /**
         * 根据用户名获取用户
         * @param username
         * @return
         */
    SysUser getUser(String username);

}
