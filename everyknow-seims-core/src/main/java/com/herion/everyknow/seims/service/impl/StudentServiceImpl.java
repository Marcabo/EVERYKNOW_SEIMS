package com.herion.everyknow.seims.service.impl;

import com.herion.everyknow.seims.dao.*;
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

    public StudentUploadListener studentUploadListener() {

        return new StudentUploadListener(studentDao,collegeDao,deptDao,clazzDao,provinceDao,locationDao);
    }

    @Override
    public Integer selectCount() {
        return null;
    }

}
