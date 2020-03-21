package com.herion.everyknow.seims.dao.impl;

import com.herion.everyknow.seims.dao.TestDao;
import com.herion.everyknow.seims.dao.entity.UserTest;
import com.herion.everyknow.seims.dao.mapper.UserTestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 测试 DaoImpl
 * @auther wubo25320
 * @create 2020-03-18 13:57
 */
@Repository
public class TestDaoImpl implements TestDao {

    @Autowired
    private UserTestMapper userTestMapper;

    @Override
    public List<UserTest> queryALl() {

        return userTestMapper.selectList(null);
    }
}
