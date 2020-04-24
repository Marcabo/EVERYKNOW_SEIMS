package com.herion.everyknow.seims.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.herion.everyknow.seims.dao.entity.SysUser;

/**
 * @Description ${description}
 * @auther wubo25320
 * @create 2020-03-24 11:05
 */
public interface SysUserService {

    /**
     * 添加用户
     * @param sysUser
     * @return
     */
    SysUser insert(SysUser sysUser);

    /**
     * 根据 用户名和密码 获取用户
     *
     * @param username
     * @param password
     * @return
     */
    SysUser getUser(String username, String password);

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    SysUser getUser(String username);

    /**
     * 根据用户 id 获取用户
     *
     * @param id
     * @return
     */
    SysUser getUserById(Integer id);

    /**
     * 根据 id 修改密码
     * @param id
     * @param password
     * @return
     */
    Integer changePasswordById(Integer id, String password);

    /**
     * 根据 用户名修改密码
     * @param username
     * @param password
     * @return
     */
    Integer changePasswordByUsername(String username, String password);

    /**
     * 根据id 修改 昵称
     * @param nickname
     * @return
     */
    Integer updateNickName(Integer id, String nickname);

    /**
     * 根据 roleId, 和 条件 分页查询
     * @param page
     * @param sysUser
     * @param roleId
     * @return
     */
    IPage<SysUser> queryPageAndRoleId(Page page, SysUser sysUser, Integer roleId);

    /**
     * 根据 id 删除用户
     * @param id
     * @return
     */
    Integer deleteById(Integer id);
}
