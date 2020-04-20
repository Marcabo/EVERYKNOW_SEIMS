package com.herion.everyknow.seims.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.herion.everyknow.seims.dao.SysPermissionDao;
import com.herion.everyknow.seims.dao.entity.SysPermission;
import com.herion.everyknow.seims.dao.mapper.SysPermissionMapper;
import com.herion.everyknow.seims.dao.mapper.SysRolePermissionMapper;
import com.herion.everyknow.seims.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 用户 权限表 数据访问层
 * @auther wubo25320
 * @create 2020-04-20 16:02
 */
@Repository
public class SysPermissionDaoImpl implements SysPermissionDao {

    @Autowired
    private SysPermissionMapper mapper;

    @Override
    public SysPermission queryById(Integer id) {
        return mapper.selectById(id);
    }

    @Override
    public List<SysPermission> queryAll(SysPermission sysPermission) {
        return mapper.selectList(this.createWrapper(sysPermission));
    }

    @Override
    public int insert(SysPermission sysPermission) {
        return mapper.insert(sysPermission);
    }

    @Override
    public int update(SysPermission sysPermission) {
        return mapper.updateById(sysPermission);
    }

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteById(id);
    }

    private LambdaQueryWrapper<SysPermission> createWrapper(SysPermission sysPermission) {
        LambdaQueryWrapper<SysPermission> wrapper = new LambdaQueryWrapper<>();
        if (sysPermission.getId() != null) {
            wrapper.eq(SysPermission::getId, sysPermission.getId());
        }
        return wrapper;
    }
}
