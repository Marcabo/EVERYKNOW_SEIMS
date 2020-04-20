package com.herion.everyknow.seims.service.impl;

import com.herion.everyknow.seims.dao.SysUserDao;
import com.herion.everyknow.seims.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.herion.everyknow.seims.dao.entity.SysUser;

import java.util.Collections;
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
}
