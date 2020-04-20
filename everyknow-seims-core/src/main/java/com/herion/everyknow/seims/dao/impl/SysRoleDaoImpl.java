package com.herion.everyknow.seims.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.herion.everyknow.seims.dao.SysRoleDao;
import com.herion.everyknow.seims.dao.entity.SysRole;
import com.herion.everyknow.seims.dao.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 用户 角色 数据访问层
 * @auther wubo25320
 * @create 2020-04-20 16:22
 */
@Repository
public class SysRoleDaoImpl implements SysRoleDao {

    @Autowired
    private SysRoleMapper mapper;

    @Override
    public SysRole queryById(Integer id) {
        return mapper.selectById(id);
    }

    @Override
    public List<SysRole> queryAll(SysRole sysRole) {
        return null;
    }

    @Override
    public int insert(SysRole sysRole) {
        return 0;
    }

    @Override
    public int update(SysRole sysRole) {
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    private LambdaQueryWrapper<SysRole> createWrapper(SysRole sysRole) {
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();

        return wrapper;
    }
}
