package com.herion.everyknow.seims.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.herion.everyknow.seims.dao.SysUserRoleDao;
import com.herion.everyknow.seims.dao.entity.SysUser;
import com.herion.everyknow.seims.dao.entity.SysUserRole;
import com.herion.everyknow.seims.dao.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 系统用户权限 数据访问层
 * @auther wubo25320
 * @create 2020-04-20 14:05
 */
@Repository
public class SysUserRoleDaoImpl implements SysUserRoleDao {

    @Autowired
    private SysUserRoleMapper mapper;

    @Override
    public SysUserRole queryById(Integer id) {
        return mapper.selectById(id);
    }

    @Override
    public List<SysUserRole> queryAll(SysUserRole sysUserRole) {
        return mapper.selectList(this.createWrapper(sysUserRole));
    }

    @Override
    public int insert(SysUserRole sysUserRole) {
        return mapper.insert(sysUserRole);
    }

    @Override
    public int update(SysUserRole sysUserRole) {
        return mapper.updateById(sysUserRole);
    }

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteById(id);
    }

    @Override
    public Integer deleteByUserId(Integer userId) {
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getUserId, userId);
        return mapper.delete(wrapper);
    }

    private LambdaQueryWrapper<SysUserRole> createWrapper(SysUserRole sysUserRole) {
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        if (sysUserRole.getId() != null) {
            wrapper.eq(SysUserRole::getId, sysUserRole.getId());
        }
        if (sysUserRole.getUserId() != null) {
            wrapper.eq(SysUserRole::getUserId, sysUserRole.getUserId());
        }
        if (sysUserRole.getRoleId() != null) {
            wrapper.eq(SysUserRole::getRoleId, sysUserRole.getRoleId());
        }
        return wrapper;
    }
}
