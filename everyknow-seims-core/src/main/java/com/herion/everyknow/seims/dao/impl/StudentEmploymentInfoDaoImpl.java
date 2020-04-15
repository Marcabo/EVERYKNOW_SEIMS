package com.herion.everyknow.seims.dao.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.herion.everyknow.seims.dao.StudentEmploymentInfoDao;
import com.herion.everyknow.seims.dao.entity.StudentEmploymentInfo;
import com.herion.everyknow.seims.dao.mapper.StudentEmploymentInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 就业信息表(StudentEmploymentInfo) 数据访问层
 * @auther wubo25320
 * @create 2020-04-15 10:52
 */
@Repository
public class StudentEmploymentInfoDaoImpl implements StudentEmploymentInfoDao {

    @Autowired
    private StudentEmploymentInfoMapper mapper;

    @Override
    public StudentEmploymentInfo queryById(Integer id) {
        return null;
    }

    @Override
    public List<StudentEmploymentInfo> queryAllByLimit(int offset, int limit) {
        return null;
    }

    @Override
    public List<StudentEmploymentInfo> queryAll(StudentEmploymentInfo studentEmploymentInfo) {
        return mapper.selectList(this.createWrapper(studentEmploymentInfo));
    }

    @Override
    public int insert(StudentEmploymentInfo studentEmploymentInfo) {
        return mapper.insert(studentEmploymentInfo);
    }

    @Override
    public int update(StudentEmploymentInfo studentEmploymentInfo) {
        return mapper.updateById(studentEmploymentInfo);
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    private LambdaQueryWrapper<StudentEmploymentInfo> createWrapper(StudentEmploymentInfo studentEmploymentInfo) {
        LambdaQueryWrapper<StudentEmploymentInfo> wrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(studentEmploymentInfo.getStuId())) {
            wrapper.eq(StudentEmploymentInfo::getStuId, studentEmploymentInfo.getStuId());
        }
        return wrapper;
    }
}
