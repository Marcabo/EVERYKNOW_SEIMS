package com.herion.everyknow.seims.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.herion.everyknow.seims.dao.IndustryTypeDao;
import com.herion.everyknow.seims.dao.entity.IndustryType;
import com.herion.everyknow.seims.dao.mapper.IndustryTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 行业类别表 数据访问层
 * @auther wubo25320
 * @create 2020-03-25 16:43
 */
@Repository
public class IndustryTypeDaoImpl implements IndustryTypeDao {

    @Autowired
    private IndustryTypeMapper mapper;

    @Override
    public IndustryType queryById(String code) {
        return mapper.selectById(code);
    }

    @Override
    public List<IndustryType> queryAll() {
        return mapper.selectList(new LambdaQueryWrapper<IndustryType>());
    }
}
