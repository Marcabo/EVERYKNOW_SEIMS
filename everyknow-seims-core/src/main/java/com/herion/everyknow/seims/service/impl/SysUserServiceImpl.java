package com.herion.everyknow.seims.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.herion.everyknow.seims.dao.SysUserDao;
import com.herion.everyknow.seims.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.herion.everyknow.seims.dao.entity.SysUser;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
  *@Description     ${description}
  *@auther          wubo25320
  *@create          2020-03-24 11:05
  */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public SysUser insert(SysUser sysUser) {
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateTime(sysUser.getCreateTime());
        sysUserDao.insert(sysUser);
        return sysUserDao.queryByUserName(sysUser.getUsername());
    }

    @Override
    public SysUser getUser(String username, String password) {
        return sysUserDao.getUser(username, password);
    }

    @Override
    public SysUser getUser(String username) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        List<SysUser> sysUserList = sysUserDao.queryAll(sysUser);
        if (sysUserList.size() <= 0) {
            return null;
        }
        return sysUserList.get(0);
    }

    @Override
    public SysUser getUserById(Integer id) {
        return sysUserDao.queryById(id);
    }

    @Override
    public Integer changePasswordById(Integer id, String password) {
        SysUser sysUser = sysUserDao.queryById(id);
        sysUser.setPassword(password);
        return sysUserDao.update(sysUser);
    }

    @Override
    public Integer changePasswordByUsername(String username, String password) {
        SysUser query = new SysUser();
        query.setUsername(username);
        SysUser sysUser = sysUserDao.queryAll(query).get(0);
        sysUser.setPassword(password);
        return sysUserDao.update(sysUser);
    }

    @Override
    public Integer updateNickName(Integer id, String nickname) {
        SysUser sysUser = sysUserDao.queryById(id);
        sysUser.setNickname(nickname);
        sysUser.setUpdateTime(new Date());
        return sysUserDao.update(sysUser);
    }

    @Override
    public IPage<SysUser> queryPageAndRoleId(Page page, SysUser sysUser, Integer roleId) {
        return sysUserDao.queryPageAndRoleId(page, sysUser, roleId);
    }

    @Override
    public Integer deleteById(Integer id) {
        return sysUserDao.deleteById(id);
    }


}
