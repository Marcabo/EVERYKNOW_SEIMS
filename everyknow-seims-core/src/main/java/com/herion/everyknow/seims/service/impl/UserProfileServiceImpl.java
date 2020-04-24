package com.herion.everyknow.seims.service.impl;

import com.herion.everyknow.seims.dao.UserProfileDao;
import com.herion.everyknow.seims.dao.entity.UserProfile;
import com.herion.everyknow.seims.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 用户个人信息(UserProfile)表服务实现类
 *
 * @author wubo25320
 * @since 2020-04-23 14:09:27
 */
@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileDao userProfileDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserProfile queryById(Integer id) {
        return this.userProfileDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param userProfile 实例对象
     * @return 实例对象
     */
    @Override
    public UserProfile insert(UserProfile userProfile) {
        int insert = this.userProfileDao.insert(userProfile);
        return userProfileDao.queryById(insert);
    }

    /**
     * 修改数据
     *
     * @param userProfile 实例对象
     * @return 实例对象
     */
    @Override
    public UserProfile update(UserProfile userProfile) {
        this.userProfileDao.update(userProfile);
        return this.queryById(userProfile.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userProfileDao.deleteById(id) > 0;
    }

    @Override
    public UserProfile queryByUserId(Integer userId) {
        return userProfileDao.queryByUserId(userId);
    }

    @Override
    public Integer updateById(UserProfile userProfile) {
        return userProfileDao.update(userProfile);
    }

    @Override
    public Integer deleteByUserId(Integer userId) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(userId);
        return userProfileDao.delete(userProfile);
    }
}