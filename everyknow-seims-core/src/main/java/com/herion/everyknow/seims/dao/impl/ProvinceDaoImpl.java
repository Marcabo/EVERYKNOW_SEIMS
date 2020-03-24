package com.herion.everyknow.seims.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.herion.everyknow.seims.dao.ProvinceDao;
import com.herion.everyknow.seims.dao.entity.Province;
import com.herion.everyknow.seims.dao.mapper.ProvinceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 省份代码表 DaoImpl
 * @auther wubo25320
 * @create 2020-03-24 13:42
 */
@Repository
public class ProvinceDaoImpl implements ProvinceDao {

    @Autowired
    private ProvinceMapper mapper;

    @Override
    public Province queryById(String code) {
        LambdaQueryWrapper<Province> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Province::getCode, code);
        return mapper.selectOne(wrapper);
    }

    @Override
    public Province queryByName(String provinceName) {
        LambdaQueryWrapper<Province> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Province::getProvinceName, provinceName);
        return mapper.selectOne(wrapper);
    }

    @Override
    public List<Province> queryAll() {
        LambdaQueryWrapper<Province> wrapper = new LambdaQueryWrapper<>();
        return mapper.selectList(wrapper);
    }
}
