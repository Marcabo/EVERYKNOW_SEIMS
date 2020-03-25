package com.herion.everyknow.seims.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.herion.everyknow.seims.dao.EmployMethodDao;
import com.herion.everyknow.seims.dao.entity.EmployMethod;
import com.herion.everyknow.seims.dao.mapper.EmployMethodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 就业方式表 数据访问层
 * @auther wubo25320
 * @create 2020-03-25 16:39
 */
@Repository
public class EmployMethodDaoImpl implements EmployMethodDao {

    @Autowired
    private EmployMethodMapper mapper;

    @Override
    public EmployMethod queryById(String code) {
        return mapper.selectById(code);
    }

    @Override
    public List<EmployMethod> queryAll() {
        return mapper.selectList(new LambdaQueryWrapper<EmployMethod>());
    }
}
