package com.herion.everyknow.seims.service;

import com.herion.everyknow.seims.dao.entity.Location;

import java.util.List;

/**
  *@Description     ${description}
  *@auther          wubo25320
  *@create          2020-03-24 11:05
  */
public interface LocationService{


    Location selectByPrimaryKey(String code);

    List<Location> selectAll();

    /**
     * 根据省份代码查询所有城市信息
     * @param provinceCode
     * @return
     */
    List<Location> selectByProvince(String provinceCode);

}
