package com.herion.everyknow.seims.service.impl;

import com.herion.everyknow.seims.dao.CompanyNatureDao;
import com.herion.everyknow.seims.service.CompanyNatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herion.everyknow.seims.dao.entity.CompanyNature;

import java.util.List;

/**
  *@Description     ${description}
  *@auther          wubo25320
  *@create          2020-03-24 11:05
  */
@Service
public class CompanyNatureServiceImpl implements CompanyNatureService {

    @Autowired
    private CompanyNatureDao companyNatureDao;

    @Override
    public CompanyNature selectByCode(String code) {
        return companyNatureDao.queryById(code);
    }

    @Override
    public List<CompanyNature> selectAll() {
        return companyNatureDao.queryAll();
    }
}
