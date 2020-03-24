package com.herion.everyknow.seims.dao.impl;

import com.herion.everyknow.seims.dao.CollegeDao;
import com.herion.everyknow.seims.dao.entity.College;
import com.herion.everyknow.seims.dao.mapper.CollegeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 学院代码 DaoImpl
 * @auther wubo25320
 * @create 2020-03-24 12:04
 */
@Repository
public class CollegeDaoImpl implements CollegeDao {

    @Autowired
    private CollegeMapper mapper;

    @Override

    public College queryById(Integer id) {
        return null;
    }

    @Override
    public List<College> queryAllByLimit(int offset, int limit) {
        return null;
    }

    @Override
    public List<College> queryAll(College college) {
        return null;
    }

    @Override
    public int insert(College college) {
        return 0;
    }

    @Override
    public int update(College college) {
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }
}
