package com.herion.everyknow.seims.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
        return mapper.selectById(id);
    }

    @Override
    public List<College> queryAll() {
        return mapper.selectList(new LambdaQueryWrapper<College>());
    }

    @Override
    public int insert(College college) {
        return mapper.insert(college);
    }

    @Override
    public int update(College college) {
        return mapper.updateById(college);
    }

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteById(id);
    }

    @Override
    public List<College> queryByName(String collegeName) {
        LambdaQueryWrapper<College> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(College::getCollegeName, collegeName);
        return mapper.selectList(wrapper);
    }

    @Override
    public College queryByCode(String collegeCode) {
        LambdaQueryWrapper<College> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(College::getCollegeCode, collegeCode);
        return mapper.selectOne(wrapper);
    }

}
