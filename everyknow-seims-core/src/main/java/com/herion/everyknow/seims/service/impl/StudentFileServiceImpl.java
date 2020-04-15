package com.herion.everyknow.seims.service.impl;

import com.herion.everyknow.seims.dao.StudentFileDao;
import com.herion.everyknow.seims.service.StudentFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.herion.everyknow.seims.dao.entity.StudentFile;

import java.util.List;

/**
  *@Description     ${description}
  *@auther          wubo25320
  *@create          2020-03-24 11:05
  */
@Service
public class StudentFileServiceImpl implements StudentFileService {

    @Autowired
    private StudentFileDao studentFileDao;

    @Override
    public StudentFile queryByStuId(String stuId) {
        StudentFile studentFile = new StudentFile();
        studentFile.setStuId(stuId);
        List<StudentFile> studentFileList = studentFileDao.queryAll(studentFile);
        if (studentFileList.size() <= 0) {
            return new StudentFile();
        }
        return studentFileList.get(0);
    }

    @Override
    public int editStudentFile(StudentFile studentFile) {
        if (studentFile.getId() != null) {
            return studentFileDao.update(studentFile);
        } else {
            return studentFileDao.insert(studentFile);
        }
    }
}
