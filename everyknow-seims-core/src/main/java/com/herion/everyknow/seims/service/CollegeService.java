package com.herion.everyknow.seims.service;

import com.herion.everyknow.seims.dao.entity.College;

import java.util.List;

/**
 * @Description 学院管理 Service 层
 * @auther wubo25320
 * @create 2020-03-24 22:10
 */
public interface CollegeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    College queryById(Integer id);

    /**
     * 获取全部数据
     *
     * @return 对象列表
     */
    List<College> queryAll();

    /**
     * 新增数据
     *
     * @param college 实例对象
     * @return 影响行数
     */
    int insert(College college);

    /**
     * 修改数据
     *
     * @param college 实例对象
     * @return 影响行数
     */
    int update(College college);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 模糊查询
     * @param collegeName
     * @return
     */
    List<College> queryByName(String collegeName);


    /**
     * 根据 code 查询
     * @param collegeCode
     * @return
     */
    College queryByCode(String collegeCode);

}
