package com.herion.everyknow.seims.dao.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.herion.everyknow.seims.dao.UserProfileDao;
import com.herion.everyknow.seims.dao.entity.UserProfile;
import com.herion.everyknow.seims.dao.mapper.UserProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 用户个人信息 DaoImpl 层
 * @auther wubo25320
 * @create 2020-04-23 14:07
 */
@Repository
public class UserProfileDaoImpl implements UserProfileDao {

    @Autowired
    private UserProfileMapper mapper;

    @Override
    public UserProfile queryById(Integer id) {
        return mapper.selectById(id);
    }

    @Override
    public List<UserProfile> queryAll(UserProfile userProfile) {
        return null;
    }

    @Override
    public int insert(UserProfile userProfile) {
        return mapper.insert(userProfile);
    }

    @Override
    public int update(UserProfile userProfile) {
        return mapper.updateById(userProfile);
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public UserProfile queryByUserId(Integer userId) {
        LambdaQueryWrapper<UserProfile> wrapper = new LambdaQueryWrapper<>();
        if (userId != null) {
            wrapper.eq(UserProfile::getUserId, userId);
        }
        return mapper.selectOne(wrapper);
    }

    @Override
    public Integer delete(UserProfile userProfile) {
        LambdaQueryWrapper<UserProfile> wrapper = new LambdaQueryWrapper<>();
        if (userProfile != null) {
            wrapper.eq(UserProfile::getUserId, userProfile.getUserId());
        }
        return mapper.delete(wrapper);
    }
}
