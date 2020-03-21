package com.herion.everyknow.seims.service;

import com.herion.everyknow.seims.dao.entity.UserTest;

import java.util.List;

/**
 * @Description 测试 Service
 * @auther wubo25320
 * @create 2020-03-18 10:10
 */
public interface TestService {

    String get();

    List<UserTest> queryAll();
}
