package com.herion.everyknow.seims.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.herion.everyknow.seims.dao.SysRolePermissionDao;
import com.herion.everyknow.seims.dao.entity.SysRolePermission;
import com.herion.everyknow.seims.dao.mapper.SysRolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 用户 角色权限表 数据访问层
 * @auther wubo25320
 * @create 2020-04-20 15:45
 */
@Repository
public class SysRolePermissionDaoImpl implements SysRolePermissionDao {

    @Autowired
    private SysRolePermissionMapper mapper;

    @Override
    public SysRolePermission queryById(Integer id) {
        return mapper.selectById(id);
    }

    @Override
    public List<SysRolePermission> queryAll(SysRolePermission sysRolePermission) {
        return mapper.selectList(this.createWrapper(sysRolePermission));
    }

    @Override
    public int insert(SysRolePermission sysRolePermission) {
        return mapper.insert(sysRolePermission);
    }

    @Override
    public int update(SysRolePermission sysRolePermission) {
        return mapper.updateById(sysRolePermission);
    }

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteById(id);
    }

    private LambdaQueryWrapper<SysRolePermission> createWrapper(SysRolePermission sysRolePermission) {
        LambdaQueryWrapper<SysRolePermission> wrapper = new LambdaQueryWrapper<>();
        if (sysRolePermission.getId() != null) {
            wrapper.eq(SysRolePermission::getId, sysRolePermission.getId());
        }
        if (sysRolePermission.getRoleId() != null) {
            wrapper.eq(SysRolePermission::getRoleId, sysRolePermission.getRoleId());
        }
        if (sysRolePermission.getPermissionId() != null) {
            wrapper.eq(SysRolePermission::getPermissionId, sysRolePermission.getPermissionId());
        }
        return wrapper;
    }
}
