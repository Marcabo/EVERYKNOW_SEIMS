package com.herion.everyknow.seims.dao.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.herion.everyknow.seims.dao.SysUserDao;
import com.herion.everyknow.seims.dao.entity.SysUser;
import com.herion.everyknow.seims.dao.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 系统用户 DaoImpl 层
 * @auther wubo25320
 * @create 2020-04-15 19:58
 */
@Repository
public class SysUserDaoImpl implements SysUserDao {

    @Autowired
    private SysUserMapper mapper;

    @Override
    public SysUser queryById(Integer id) {
        return null;
    }

    @Override
    public SysUser getUser(String username, String password) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, username).eq(SysUser::getPassword, password);
        return mapper.selectOne(wrapper);
    }

    @Override
    public List<SysUser> queryAllByLimit(int offset, int limit) {
        return null;
    }

    @Override
    public List<SysUser> queryAll(SysUser sysUser) {
        return mapper.selectList(this.createWrapper(sysUser));
    }

    @Override
    public int insert(SysUser sysUser) {
        return 0;
    }

    @Override
    public int update(SysUser sysUser) {
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    private LambdaQueryWrapper<SysUser> createWrapper(SysUser sysUser) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        if (sysUser.getId() != null) {
            wrapper.eq(SysUser::getId, sysUser.getId());
        }
        if (StrUtil.isNotBlank(sysUser.getUsername())) {
            wrapper.eq(SysUser::getUsername, sysUser.getUsername());
        }
        if (StrUtil.isNotBlank(sysUser.getPassword())) {
            wrapper.eq(SysUser::getPassword, sysUser.getPassword());
        }
        return wrapper;
    }
}
