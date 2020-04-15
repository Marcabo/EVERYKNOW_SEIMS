package com.herion.everyknow.seims.service;

import com.herion.everyknow.seims.dao.entity.StudentEmploymentInfo;
import com.herion.everyknow.seims.dao.entity.StudentFile;
    /**
  *@Description     ${description}
  *@auther          wubo25320
  *@create          2020-03-24 11:05
  */
public interface StudentFileService{
        /**
         * 根据 stuId 查询 档案信息
         * @param stuId
         * @return
         */
        StudentFile queryByStuId(String stuId);

        /**
         * 更新 或 添加 学生档案信息
         * id 存在就是 更新, id 不存在就是 添加
         * @param studentFile
         * @return
         */
        int editStudentFile(StudentFile studentFile);
}
