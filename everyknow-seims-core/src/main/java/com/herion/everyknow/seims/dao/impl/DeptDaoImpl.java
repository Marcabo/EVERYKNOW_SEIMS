package com.herion.everyknow.seims.dao.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.herion.everyknow.seims.dao.DeptDao;
import com.herion.everyknow.seims.dao.entity.Dept;
import com.herion.everyknow.seims.dao.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 专业表(Dept)数据库访问层
 * @auther wubo25320
 * @create 2020-03-24 22:33
 */
@Repository
public class DeptDaoImpl implements DeptDao {

    @Autowired
    private DeptMapper mapper;


    @Override
    public Dept queryById(Integer id) {
        return mapper.selectById(id);
    }

    @Override
    public List<Dept> queryAll() {
        return mapper.selectList(new LambdaQueryWrapper<Dept>());
    }

    @Override
    public List<Dept> queryList(Dept dept) {
        LambdaQueryWrapper<Dept> wrapper = this.createLikeWrapper(dept);
        return mapper.selectList(wrapper);
    }

    @Override
    public int insert(Dept dept) {
        return mapper.insert(dept);
    }

    @Override
    public int updateById(Dept dept) {
        return mapper.updateById(dept);
    }

    @Override
    public int updateCollegeCode(Dept dept, String oldCollegeCode) {
        LambdaUpdateWrapper<Dept> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Dept::getCollegeCode, oldCollegeCode);
        return mapper.update(dept, updateWrapper);
    }

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteById(id);
    }

    @Override
    public int delete(Dept dept) {
        return mapper.delete(createWrapper(dept));
    }

    @Override
    public List<Dept> queryByName(String deptName) {
        LambdaQueryWrapper<Dept> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Dept::getDeptName, deptName);
        return mapper.selectList(wrapper);
    }

    private LambdaQueryWrapper<Dept> createWrapper(Dept dept) {
        LambdaQueryWrapper<Dept> wrapper = new LambdaQueryWrapper<>();
        if (dept.getId() != null) {
            wrapper.eq(Dept::getId, dept.getId());
        }
        if (StrUtil.isNotBlank(dept.getDeptName())) {
            wrapper.eq(Dept::getDeptName, dept.getDeptName());
        }
        if (StrUtil.isNotBlank(dept.getDeptCode())) {
            wrapper.eq(Dept::getDeptCode, dept.getDeptCode());
        }
        if (StrUtil.isNotBlank(dept.getCollegeCode())) {
            wrapper.eq(Dept::getCollegeCode, dept.getCollegeCode());
        }

        return wrapper;
    }

    private LambdaQueryWrapper<Dept> createLikeWrapper(Dept dept) {
        LambdaQueryWrapper<Dept> wrapper = new LambdaQueryWrapper<>();
        if (dept.getId() != null) {
            wrapper.eq(Dept::getId, dept.getId());
        }
        if (StrUtil.isNotBlank(dept.getDeptName())) {
            wrapper.like(Dept::getDeptName, dept.getDeptName());
        }
        if (StrUtil.isNotBlank(dept.getDeptCode())) {
            wrapper.eq(Dept::getDeptCode, dept.getDeptCode());
        }
        if (StrUtil.isNotBlank(dept.getCollegeCode())) {
            wrapper.eq(Dept::getCollegeCode, dept.getCollegeCode());
        }

        return wrapper;
    }
}
