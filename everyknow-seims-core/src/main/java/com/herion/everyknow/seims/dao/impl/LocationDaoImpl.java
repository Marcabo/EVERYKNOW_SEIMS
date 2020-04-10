package com.herion.everyknow.seims.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.herion.everyknow.seims.dao.LocationDao;
import com.herion.everyknow.seims.dao.entity.Location;
import com.herion.everyknow.seims.dao.mapper.LocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 地区代码 DaoImpl
 * @auther wubo25320
 * @create 2020-03-24 9:22
 */
@Repository
public class LocationDaoImpl implements LocationDao {

    @Autowired
    private LocationMapper mapper;

    @Override
    public Location queryById(String code) {
        LambdaQueryWrapper<Location> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Location::getCode, code);
        return mapper.selectOne(wrapper);
    }

    @Override
    public List<Location> queryAll() {
        LambdaQueryWrapper<Location> wrapper = new LambdaQueryWrapper<>();
        return mapper.selectList(wrapper);
    }

    @Override
    public List<Location> queryProvince(String provinceCode) {
        LambdaQueryWrapper<Location> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Location::getProvinceCode, provinceCode);
        return mapper.selectList(wrapper);
    }

    @Override
    public List<Location> queryByCityName(String cityName) {
        LambdaQueryWrapper<Location> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Location::getCityName, cityName);
        return mapper.selectList(wrapper);
    }

}
