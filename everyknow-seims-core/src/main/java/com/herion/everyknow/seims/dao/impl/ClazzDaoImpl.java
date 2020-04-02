package com.herion.everyknow.seims.dao.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.herion.everyknow.seims.dao.ClazzDao;
import com.herion.everyknow.seims.dao.entity.Clazz;
import com.herion.everyknow.seims.dao.mapper.ClazzMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 班级代码 DaoImpl
 * @auther wubo25320
 * @create 2020-03-24 12:03
 */
@Repository
public class ClazzDaoImpl implements ClazzDao {

    @Autowired
    private ClazzMapper mapper;


    @Override
    public Clazz queryById(Integer id) {
        return mapper.selectById(id);
    }

    @Override
    public List<Clazz> queryAll() {
        return mapper.selectList(new LambdaQueryWrapper<Clazz>());
    }

    @Override
    public List<Clazz> queryListLike(Clazz clazz) {
        return mapper.selectList(this.createLikeWrapper(clazz));
    }

    @Override
    public int insert(Clazz clazz) {
        return mapper.insert(clazz);
    }

    @Override
    public int updateById(Clazz clazz) {
        return mapper.updateById(clazz);
    }

    @Override
    public int updateCollegeCode(Clazz clazz, String collegeCode) {
        LambdaUpdateWrapper<Clazz> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Clazz::getCollegeCode, collegeCode);
        return mapper.update(clazz, wrapper);
    }

    @Override
    public int updateDeptCode(Clazz clazz, String deptCode) {
        LambdaUpdateWrapper<Clazz> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Clazz::getDeptCode, deptCode);
        return mapper.update(clazz, wrapper);
    }

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteById(id);
    }

    @Override
    public int delete(Clazz clazz) {
        return mapper.delete(this.createWrapper(clazz));
    }

    private LambdaQueryWrapper<Clazz> createWrapper(Clazz clazz) {
        LambdaQueryWrapper<Clazz> wrapper = new LambdaQueryWrapper<>();
        if (clazz.getId() != null) {
            wrapper.eq(Clazz::getId, clazz.getId());
        }
        if (StrUtil.isNotBlank(clazz.getClazzName())) {
            wrapper.eq(Clazz::getClazzName, clazz.getClazzName());
        }
        if (StrUtil.isNotBlank(clazz.getCollegeCode())) {
            wrapper.eq(Clazz::getCollegeCode, clazz.getCollegeCode());
        }
        if (StrUtil.isNotBlank(clazz.getDeptCode())) {
            wrapper.eq(Clazz::getDeptCode, clazz.getDeptCode());
        }
        return wrapper;
    }

    private LambdaQueryWrapper<Clazz> createLikeWrapper(Clazz clazz) {
        LambdaQueryWrapper<Clazz> wrapper = new LambdaQueryWrapper<>();
        if (clazz.getId() != null) {
            wrapper.eq(Clazz::getId, clazz.getId());
        }
        if (StrUtil.isNotBlank(clazz.getClazzName())) {
            wrapper.like(Clazz::getClazzName, clazz.getClazzName());
        }
        if (StrUtil.isNotBlank(clazz.getCollegeCode())) {
            wrapper.eq(Clazz::getCollegeCode, clazz.getCollegeCode());
        }
        if (StrUtil.isNotBlank(clazz.getDeptCode())) {
            wrapper.eq(Clazz::getDeptCode, clazz.getDeptCode());
        }
        return wrapper;
    }
}
