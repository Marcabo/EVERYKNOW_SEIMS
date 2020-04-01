package com.herion.everyknow.seims.service;

import com.herion.everyknow.seims.dao.entity.Dept;

import java.util.List;

/**
  *@Description     ${description}
  *@auther          wubo25320
  *@create          2020-03-24 11:05
  */
public interface DeptService{


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Dept queryById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @return 对象列表
     */
    List<Dept> queryAll();

    /**
     * 条件查找
     * @param dept
     * @return
     */
    List<Dept> queryList(Dept dept);

    /**
     * 新增数据
     *
     * @param dept 实例对象
     * @return 影响行数
     */
    int insert(Dept dept);

    /**
     * 修改数据
     *
     * @param dept 实例对象
     * @return 影响行数
     */
    int update(Dept dept);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 根据 deptCode 查询
     * @param deptCode
     * @return
     */
    Dept queryByCode(String deptCode);

    /**
     * 根据 deptName 模糊查询
     * @param deptName
     * @return
     */
    List<Dept> queryByName(String deptName);

}
