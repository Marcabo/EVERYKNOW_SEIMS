package com.herion.everyknow.seims.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.herion.everyknow.seims.dao.ClazzDao;
import com.herion.everyknow.seims.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.herion.everyknow.seims.dao.entity.Clazz;

import java.util.List;

/**
  *@Description     ${description}
  *@auther          wubo25320
  *@create          2020-03-24 11:05
  */
@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzDao clazzDao;

    @Override
    public Clazz queryById(Integer id) {
        return clazzDao.queryById(id);
    }

    @Override
    public List<Clazz> queryAll() {
        return clazzDao.queryAll();
    }

    @Override
    public IPage<Clazz> queryPage(Page page) {
        return clazzDao.queryPage(page);
    }

    @Override
    public List<Clazz> queryLike(Clazz clazz) {
        return clazzDao.queryListLike(clazz);
    }

    @Override
    public IPage<Clazz> queryPageLike(Page page, Clazz clazz) {
        return clazzDao.queryPageListLike(page, clazz);
    }

    @Override
    public int deleteById(Integer id) {
        return clazzDao.deleteById(id);
    }

    @Override
    public int insert(Clazz record) {
        return clazzDao.insert(record);
    }

    @Override
    public int update(Clazz record) {
        return clazzDao.updateById(record);
    }
}
