package com.herion.everyknow.seims.service.impl;

import com.herion.everyknow.seims.dao.TestDao;
import com.herion.everyknow.seims.dao.entity.UserTest;
import com.herion.everyknow.seims.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 测试 ServiceImpl
 * @auther wubo25320
 * @create 2020-03-18 10:11
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    public String get() {
        return "我是 ServiceImpl 的返回";
    }

    @Override
    public List<UserTest> queryAll() {
        return testDao.queryALl();
    }
}
