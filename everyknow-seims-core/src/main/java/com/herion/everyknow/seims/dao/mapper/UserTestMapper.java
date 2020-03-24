package com.herion.everyknow.seims.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.herion.everyknow.seims.dao.entity.UserTest;

/**
 * @Description ${description}
 * @auther wubo25320
 * @create 2020-03-24 10:48
 */
public interface UserTestMapper extends BaseMapper<UserTest> {
    int deleteByPrimaryKey(Long id);

    int insert(UserTest record);

    int insertSelective(UserTest record);

    UserTest selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserTest record);

    int updateByPrimaryKey(UserTest record);
}