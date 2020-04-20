package com.herion.everyknow.seims.service;

import com.herion.everyknow.seims.dao.entity.SysRolePermission;

import java.util.List;

/**
 * 角色权限表(SysRolePermission)表服务接口
 *
 * @author wubo25320
 * @since 2020-04-20 15:51:07
 */
public interface SysRolePermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysRolePermission queryById(Integer id);

    /**
     * 新增数据
     *
     * @param sysRolePermission 实例对象
     * @return 实例对象
     */
    int insert(SysRolePermission sysRolePermission);

    /**
     * 修改数据
     *
     * @param sysRolePermission 实例对象
     * @return 实例对象
     */
    int update(SysRolePermission sysRolePermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 根据 roleId 获取 角色权限表
     * @param roleId
     * @return
     */
    List<SysRolePermission> queryByRoleId(Integer roleId);

}