package com.herion.everyknow.seims.dao;

import com.herion.everyknow.seims.dao.entity.Dept;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 专业表(Dept)表数据库访问层
 *
 * @author wubo25320
 * @since 2020-03-23 23:47:10
 */
public interface DeptDao {

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
    int updateById(Dept dept);

    /**
     * 修改数据
     * @param dept 实例对象
     * @Param oldCollegeCode 旧CollegeCode
     * @return 影响行数
     */
    int updateCollegeCode(Dept dept, String oldCollegeCode);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 条件删除
     * @param dept
     * @return
     */
    int delete(Dept dept);

    /**
     * 根据 deptName 模糊查询
     * @param deptName
     * @return
     */
    List<Dept> queryByName(String deptName);

}