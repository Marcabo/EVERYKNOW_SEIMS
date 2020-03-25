package com.herion.everyknow.seims.service.impl;

import com.herion.everyknow.seims.dao.IndustryTypeDao;
import com.herion.everyknow.seims.service.IndustryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herion.everyknow.seims.dao.entity.IndustryType;

import java.util.List;

/**
  *@Description     ${description}
  *@auther          wubo25320
  *@create          2020-03-24 11:05
  */
@Service
public class IndustryTypeServiceImpl implements IndustryTypeService {

    @Autowired
    private IndustryTypeDao industryTypeDao;

    @Override
    public IndustryType selectByCode(String code) {
        return industryTypeDao.queryById(code);
    }

    @Override
    public List<IndustryType> selectAll() {
        return industryTypeDao.queryAll();
    }
}
