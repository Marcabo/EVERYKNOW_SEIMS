package com.herion.everyknow.seims.service;


import com.herion.everyknow.seims.dao.entity.UserProfile;

import java.util.List;

/**
 * 用户个人信息(UserProfile)表服务接口
 *
 * @author wubo25320
 * @since 2020-04-23 14:09:27
 */
public interface UserProfileService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserProfile queryById(Integer id);

    /**
     * 新增数据
     *
     * @param userProfile 实例对象
     * @return 实例对象
     */
    UserProfile insert(UserProfile userProfile);

    /**
     * 修改数据
     *
     * @param userProfile 实例对象
     * @return 实例对象
     */
    UserProfile update(UserProfile userProfile);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 根据 用户id 查找用户个人信息
     * @param userId
     * @return
     */
    UserProfile queryByUserId(Integer userId);

    /**
     * 根据 id 更新个人信息
     * @param userProfile
     * @return
     */
    Integer updateById(UserProfile userProfile);

    /**
     * 根据 用户id 删除 个人信息
     * @param userId
     * @return
     */
    Integer deleteByUserId(Integer userId);

}