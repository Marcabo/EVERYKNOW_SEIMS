package com.herion.everyknow.seims.service.impl;

import com.herion.everyknow.seims.dao.SysPermissionDao;
import com.herion.everyknow.seims.dao.entity.SysPermission;
import com.herion.everyknow.seims.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限表(SysPermission)表服务实现类
 *
 * @author wubo25320
 * @since 2020-04-20 16:08:04
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionDao sysPermissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysPermission queryById(Integer id) {
        return this.sysPermissionDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param sysPermission 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(SysPermission sysPermission) {
        return this.sysPermissionDao.insert(sysPermission);
    }

    /**
     * 修改数据
     *
     * @param sysPermission 实例对象
     * @return 实例对象
     */
    @Override
    public int update(SysPermission sysPermission) {
        return this.sysPermissionDao.update(sysPermission);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.sysPermissionDao.deleteById(id) > 0;
    }
}