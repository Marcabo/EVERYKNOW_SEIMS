package com.herion.everyknow.seims.service.impl;

import com.herion.everyknow.seims.dao.ProvinceDao;
import com.herion.everyknow.seims.dao.entity.Province;
import com.herion.everyknow.seims.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
  *@Description     ${description}
  *@auther          wubo25320
  *@create          2020-03-24 11:05
  */
@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceDao provinceDao;


    @Override
    public Province selectByCode(String code) {
        return provinceDao.queryById(code);
    }

    @Override
    public Province selectByName(String provinceName) {
        return provinceDao.queryByName(provinceName);
    }


    @Override
    public List<Province> selectAll() {
        return provinceDao.queryAll();
    }
}
