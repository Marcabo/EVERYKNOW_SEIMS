package com.herion.everyknow.seims.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.herion.everyknow.seims.dao.CompanyNatureDao;
import com.herion.everyknow.seims.dao.entity.CompanyNature;
import com.herion.everyknow.seims.dao.mapper.CompanyNatureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 单位性质(CompanyNature)表数据库访问层
 * @auther wubo25320
 * @create 2020-03-25 16:13
 */
@Repository
public class CompanyNatureDaoImpl implements CompanyNatureDao {

    @Autowired
    private CompanyNatureMapper mapper;

    @Override
    public CompanyNature queryById(String code) {
        return mapper.selectById(code);
    }

    @Override
    public List<CompanyNature> queryAll() {
        return mapper.selectList(new LambdaQueryWrapper<CompanyNature>());
    }
}
