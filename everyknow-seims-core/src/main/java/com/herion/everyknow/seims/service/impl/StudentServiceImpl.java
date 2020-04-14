package com.herion.everyknow.seims.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.herion.everyknow.common.exception.EKnowException;
import com.herion.everyknow.seims.dao.*;
import com.herion.everyknow.seims.dao.entity.Student;
import com.herion.everyknow.seims.service.StudentService;
import com.herion.everyknow.seims.service.listener.StudentUploadListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
  *@Description     ${description}
  *@auther          wubo25320
  *@create          2020-03-24 11:05
  */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private CollegeDao collegeDao;

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private ClazzDao clazzDao;

    @Autowired
    private ProvinceDao provinceDao;

    @Autowired
    private LocationDao locationDao;

    @Override
    public int insert(Student student) {
        if (studentDao.queryAll(Student.builder().stuId(student.getStuId()).build()).size() >= 0) {
            throw new EKnowException("学号不能重复");
        }
        return studentDao.insert(student);
    }

    @Override
    public int updateById(Student student) {
        if (studentDao.checkExist(student)) {
            throw new EKnowException("学号不能重复");
        }
        return studentDao.update(student);
    }

    public StudentUploadListener studentUploadListener() {
        return new StudentUploadListener(studentDao,collegeDao,deptDao,clazzDao,provinceDao,locationDao);
    }

    @Override
    public Student queryById(Integer id) {
        return studentDao.queryById(id);
    }

    @Override
    public Integer selectCount() {
        return null;
    }

    @Override
    public IPage<Student> queryPage(Page page, Student student) {
        IPage<Student> studentIPage = studentDao.queryPage(page, student);
        return studentIPage;
    }

    @Override
    public IPage<Student> queryNoEmployPage(Page page, Student student) {
        return studentDao.queryNoEmployPage(page, student);
    }

    @Override
    public IPage<Student> queryNoFilePage(Page page, Student student) {
        return studentDao.queryNoFilePage(page, student);
    }
}
