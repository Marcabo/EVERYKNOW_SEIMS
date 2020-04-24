package com.herion.everyknow.seims.dao;

import com.herion.everyknow.seims.dao.entity.UserProfile;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 用户个人信息(UserProfile)表数据库访问层
 *
 * @author wubo25320
 * @since 2020-04-23 14:05:59
 */
public interface UserProfileDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserProfile queryById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param userProfile 实例对象
     * @return 对象列表
     */
    List<UserProfile> queryAll(UserProfile userProfile);

    /**
     * 新增数据
     *
     * @param userProfile 实例对象
     * @return 影响行数
     */
    int insert(UserProfile userProfile);

    /**
     * 修改数据
     *
     * @param userProfile 实例对象
     * @return 影响行数
     */
    int update(UserProfile userProfile);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 根据 用户id 查找用户个人信息
     * @param userId
     * @return
     */
    UserProfile queryByUserId(Integer userId);

    /**
     * 条件删除 个人信息
     * @param userProfile
     * @return
     */
    Integer delete(UserProfile userProfile);
}