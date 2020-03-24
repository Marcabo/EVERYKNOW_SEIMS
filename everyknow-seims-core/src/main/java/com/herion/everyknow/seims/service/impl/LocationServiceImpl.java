package com.herion.everyknow.seims.service.impl;

import com.herion.everyknow.seims.dao.LocationDao;
import com.herion.everyknow.seims.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herion.everyknow.seims.dao.entity.Location;

import java.util.List;

/**
  *@Description     ${description}
  *@auther          wubo25320
  *@create          2020-03-24 11:05
  */
@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationDao locationDao;

    @Override
    public Location selectByPrimaryKey(String code) {
        return locationDao.queryById(code);
    }

    @Override
    public List<Location> selectAll() {
        return locationDao.queryAll();
    }

    @Override
    public List<Location> selectByProvince(String provinceCode) {
        return locationDao.queryProvince(provinceCode);
    }

}
