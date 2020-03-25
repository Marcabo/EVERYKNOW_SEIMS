package com.herion.everyknow.seims.service;

import com.herion.everyknow.seims.dao.entity.CompanyNature;

import java.util.List;

/**
  *@Description     ${description}
  *@auther          wubo25320
  *@create          2020-03-24 11:05
  */
public interface CompanyNatureService{

    /**
     * 根据 code 查询
     * @param code
     * @return
     */
    CompanyNature selectByCode(String code);

    /**
     * 查询全部
     * @return
     */
    List<CompanyNature> selectAll();


}
