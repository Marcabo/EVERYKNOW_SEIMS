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
     * 通过实体作为筛选条件查询
     *
     * @return 对象列表
     */
    List<Clazz> queryAll();

    /**
     * 通过实体作为筛选条件
     * @param clazz
     * @return
     */
    List<Clazz> queryList(Clazz clazz);

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
    int updateById(Clazz clazz);

    /**
     * 根据 collegeCode 更新
     * @param collegeCode
     * @param clazz
     * @return
     */
    int updateCollegeCode(Clazz clazz, String collegeCode);

    /**
     * 根据 deptCode 更新
     * @param clazz
     * @param deptCode
     * @return
     */
    int updateDeptCode(Clazz clazz, String deptCode);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 条件删除
     * @param clazz
     * @return
     */
    int delete(Clazz clazz);

}