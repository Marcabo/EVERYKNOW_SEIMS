package com.herion.everyknow.seims.service;

import com.herion.everyknow.seims.dao.entity.SysUserRole;
import java.util.List;

/**
 * 用户角色表(SysUserRole)表服务接口
 *
 * @author wubo25320
 * @since 2020-04-20 14:03:22
 */
public interface SysUserRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUserRole queryById(Integer id);

    /**
     * 新增数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    SysUserRole insert(SysUserRole sysUserRole);

    /**
     * 修改数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    SysUserRole update(SysUserRole sysUserRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}