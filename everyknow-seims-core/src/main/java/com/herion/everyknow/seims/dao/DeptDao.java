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
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Dept> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param dept 实例对象
     * @return 对象列表
     */
    List<Dept> queryAll(Dept dept);

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

}