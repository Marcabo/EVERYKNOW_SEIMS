package com.herion.everyknow.seims.service;

import com.herion.everyknow.seims.dao.entity.Student;
import com.herion.everyknow.seims.service.listener.StudentUploadListener;

/**
  *@Description     ${description}
  *@auther          wubo25320
  *@create          2020-03-24 11:05
  */
public interface StudentService{

    StudentUploadListener studentUploadListener();

    /**
     * 统计学生个数
     * @return
     */
    Integer selectCount();

}
