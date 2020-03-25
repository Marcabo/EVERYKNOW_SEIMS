package com.herion.everyknow.seims.service;

import com.herion.everyknow.seims.dao.entity.EmployMethod;

import java.util.List;

/**
  *@Description     ${description}
  *@auther          wubo25320
  *@create          2020-03-24 11:05
  */
public interface EmployMethodService{

    /**
     * 根据 code 查询
     * @param code
     * @return
     */
    EmployMethod selectByCode(String code);

    /**
     * 查询全部
     * @return
     */
    List<EmployMethod> selectAll();

}
