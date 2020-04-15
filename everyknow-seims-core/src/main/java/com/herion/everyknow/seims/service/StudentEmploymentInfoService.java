package com.herion.everyknow.seims.service;

import com.herion.everyknow.seims.dao.entity.StudentEmploymentInfo;

/**
  *@Description     ${description}
  *@auther          wubo25320
  *@create          2020-03-24 11:05
  */
public interface StudentEmploymentInfoService{

    /**
     * 根据 stuId 查询 就业信息
     * @param stuId
     * @return
     */
    StudentEmploymentInfo queryByStuId(String stuId);

    /**
     * 更新 或 添加 学生就业信息
     * id 存在就是 更新, id 不存在就是 添加
     * @param studentEmploymentInfo
     * @return
     */
    int editStudentEmployment(StudentEmploymentInfo studentEmploymentInfo);
}
