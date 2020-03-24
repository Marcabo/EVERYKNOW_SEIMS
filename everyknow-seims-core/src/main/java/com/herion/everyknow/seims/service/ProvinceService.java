package com.herion.everyknow.seims.service;

import com.herion.everyknow.seims.dao.entity.Province;

import java.util.List;

/**
  *@Description     ${description}
  *@auther          wubo25320
  *@create          2020-03-24 11:05
  */
public interface ProvinceService{


    /**
     * 通过省份编码获取省份
     * @param code
     * @return
     */
    Province selectByCode(String code);

    /**
     * 通过省份名称获取省份
     * @param provinceName
     * @return
     */
    Province selectByName(String provinceName);

    /**
     * 获取全部省份
     * @return
     */
    List<Province> selectAll();
}
