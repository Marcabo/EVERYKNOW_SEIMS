package com.herion.everyknow.seims.service.impl;

import com.herion.everyknow.seims.dao.EmployMethodDao;
import com.herion.everyknow.seims.service.EmployMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herion.everyknow.seims.dao.entity.EmployMethod;

import java.util.List;

/**
  *@Description     ${description}
  *@auther          wubo25320
  *@create          2020-03-24 11:05
  */
@Service
public class EmployMethodServiceImpl implements EmployMethodService {

    @Autowired
    private EmployMethodDao employMethodDao;

    @Override
    public EmployMethod selectByCode(String code) {
        return employMethodDao.queryById(code);
    }

    @Override
    public List<EmployMethod> selectAll() {
        return employMethodDao.queryAll();
    }
}
