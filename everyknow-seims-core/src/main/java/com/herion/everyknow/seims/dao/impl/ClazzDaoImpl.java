package com.herion.everyknow.seims.dao.impl;

import com.herion.everyknow.seims.dao.ClazzDao;
import com.herion.everyknow.seims.dao.entity.Clazz;
import com.herion.everyknow.seims.dao.mapper.ClazzMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 班级代码 DaoImpl
 * @auther wubo25320
 * @create 2020-03-24 12:03
 */
@Repository
public class ClazzDaoImpl implements ClazzDao {

    @Autowired
    private ClazzMapper mapper;

    @Override
    public Clazz queryById(Integer id) {
        return null;
    }

    @Override
    public List<Clazz> queryAllByLimit(int offset, int limit) {
        return null;
    }

    @Override
    public List<Clazz> queryAll(Clazz clazz) {
        return null;
    }

    @Override
    public int insert(Clazz clazz) {
        return 0;
    }

    @Override
    public int update(Clazz clazz) {
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }
}
