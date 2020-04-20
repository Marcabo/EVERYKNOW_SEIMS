package com.herion.everyknow.seims.service;


import com.herion.everyknow.seims.dao.entity.SysPermission;

/**
 * 权限表(SysPermission)表服务接口
 *
 * @author wubo25320
 * @since 2020-04-20 16:08:04
 */
public interface SysPermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysPermission queryById(Integer id);

    /**
     * 新增数据
     *
     * @param sysPermission 实例对象
     * @return 实例对象
     */
    int insert(SysPermission sysPermission);

    /**
     * 修改数据
     *
     * @param sysPermission 实例对象
     * @return 实例对象
     */
    int update(SysPermission sysPermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}