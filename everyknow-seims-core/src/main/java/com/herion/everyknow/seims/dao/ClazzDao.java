package com.herion.everyknow.seims.dao;

import com.herion.everyknow.seims.dao.entity.Clazz;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 班级表(Clazz)表数据库访问层
 *
 * @author wubo25320
 * @since 2020-03-23 23:47:10
 */
public interface ClazzDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Clazz queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Clazz> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param clazz 实例对象
     * @return 对象列表
     */
    List<Clazz> queryAll(Clazz clazz);

    /**
     * 新增数据
     *
     * @param clazz 实例对象
     * @return 影响行数
     */
    int insert(Clazz clazz);

    /**
     * 修改数据
     *
     * @param clazz 实例对象
     * @return 影响行数
     */
    int update(Clazz clazz);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}