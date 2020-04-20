package com.herion.everyknow.seims.dao;

import com.herion.everyknow.seims.dao.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 用户角色表(SysUserRole)表数据库访问层
 *
 * @author wubo25320
 * @since 2020-04-20 14:02:35
 */
public interface SysUserRoleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUserRole queryById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysUserRole 实例对象
     * @return 对象列表
     */
    List<SysUserRole> queryAll(SysUserRole sysUserRole);

    /**
     * 新增数据
     *
     * @param sysUserRole 实例对象
     * @return 影响行数
     */
    int insert(SysUserRole sysUserRole);

    /**
     * 修改数据
     *
     * @param sysUserRole 实例对象
     * @return 影响行数
     */
    int update(SysUserRole sysUserRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}