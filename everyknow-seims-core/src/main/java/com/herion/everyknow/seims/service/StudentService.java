package com.herion.everyknow.seims.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.herion.everyknow.seims.dao.entity.Student;
import com.herion.everyknow.seims.service.listener.StudentUploadListener;

/**
  *@Description     ${description}
  *@auther          wubo25320
  *@create          2020-03-24 11:05
  */
public interface StudentService{

    int insert(Student student);

    int updateById(Student student);

    StudentUploadListener studentUploadListener();

    /**
     * 根据 id 获取学生
     * @param id
     * @return
     */
    Student queryById(Integer id);

    /**
     * 根据 StuId 获取学生
     * @param stuId
     * @return
     */
    Student queryByStuId(String stuId);

    /**
     * 统计学生个数
     * @return
     */
    Integer selectCount();

    /**
     * 分页条件查询
     * @param page
     * @param student
     * @return
     */
    IPage<Student> queryPage(Page page, Student student);

    /**
     * 根据条件分页查询未就业学生列表
     * @param page
     * @param student
     * @return
     */
    IPage<Student> queryNoEmployPage(Page page, Student student);

    /**
     * 根据条件分页查询未登记档案学生列表
     * @param page
     * @param student
     * @return
     */
    IPage<Student> queryNoFilePage(Page page, Student student);
}
