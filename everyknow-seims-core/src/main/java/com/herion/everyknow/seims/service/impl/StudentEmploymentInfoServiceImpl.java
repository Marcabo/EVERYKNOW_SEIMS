package com.herion.everyknow.seims.service.impl;

import com.herion.everyknow.seims.dao.StudentEmploymentInfoDao;
import com.herion.everyknow.seims.service.StudentEmploymentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herion.everyknow.seims.dao.entity.StudentEmploymentInfo;

import java.util.Collections;
import java.util.List;

/**
  *@Description     ${description}
  *@auther          wubo25320
  *@create          2020-03-24 11:05
  */
@Service
public class StudentEmploymentInfoServiceImpl implements StudentEmploymentInfoService {

    @Autowired
    private StudentEmploymentInfoDao studentEmploymentInfoDao;

    @Override
    public StudentEmploymentInfo queryByStuId(String stuId) {
        StudentEmploymentInfo studentEmploymentInfo = new StudentEmploymentInfo();
        studentEmploymentInfo.setStuId(stuId);
        List<StudentEmploymentInfo> studentEmploymentInfoList = studentEmploymentInfoDao.queryAll(studentEmploymentInfo);
        if (studentEmploymentInfoList.size() <= 0) {
            return new StudentEmploymentInfo();
        }
        return studentEmploymentInfoList.get(0);
    }

    @Override
    public int editStudentEmployment(StudentEmploymentInfo studentEmploymentInfo) {
        if (studentEmploymentInfo.getId() != null) {
            // 如果 id 不为空, 就是更新
            return studentEmploymentInfoDao.update(studentEmploymentInfo);
        } else {
            return studentEmploymentInfoDao.insert(studentEmploymentInfo);
        }
    }
}
