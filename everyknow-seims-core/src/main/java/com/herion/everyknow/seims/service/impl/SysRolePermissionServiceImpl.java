package com.herion.everyknow.seims.service.impl;

import com.herion.everyknow.seims.dao.SysRolePermissionDao;
import com.herion.everyknow.seims.dao.entity.SysRolePermission;
import com.herion.everyknow.seims.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @Description 用户 角色权限表 业务层
 * @auther wubo25320
 * @create 2020-04-20 15:51
 */
@Service
public class SysRolePermissionServiceImpl implements SysRolePermissionService {

    @Autowired
    private SysRolePermissionDao sysRolePermissionDao;

    @Override
    public SysRolePermission queryById(Integer id) {
        return sysRolePermissionDao.queryById(id);
    }

    @Override
    public int insert(SysRolePermission sysRolePermission) {
        return sysRolePermissionDao.insert(sysRolePermission);
    }

    @Override
    public int update(SysRolePermission sysRolePermission) {
        return sysRolePermissionDao.update(sysRolePermission);
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public List<SysRolePermission> queryByRoleId(Integer roleId) {
        SysRolePermission query = new SysRolePermission();
        query.setRoleId(roleId);
        List<SysRolePermission> sysRolePermissionList = sysRolePermissionDao.queryAll(query);
        if (sysRolePermissionList == null) {
            return Collections.EMPTY_LIST;
        }
        return sysRolePermissionList;
    }
}
