package com.herion.everyknow.seims.dao.impl;

import com.herion.everyknow.seims.dao.StudentDao;
import com.herion.everyknow.seims.dao.entity.Student;
import com.herion.everyknow.seims.dao.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 学生基本信息表(Student) DaoImpl
 * @auther wubo25320
 * @create 2020-04-05 16:24
 */
@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private StudentMapper mapper;

    @Override
    public Student queryById(Integer id) {
        return null;
    }

    @Override
    public List<Student> queryAllByLimit(int offset, int limit) {
        return null;
    }

    @Override
    public List<Student> queryAll(Student student) {
        return null;
    }

    @Override
    public int insert(Student student) {
        return 0;
    }

    @Override
    public int update(Student student) {
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public int insertBatch(List<Student> list) {
        return mapper.insertBatchSomeColumn(list);
    }
}
