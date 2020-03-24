package com.herion.everyknow.seims.dao;

import com.herion.everyknow.seims.dao.entity.CompanyNature;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 单位性质(CompanyNature)表数据库访问层
 *
 * @author wubo25320
 * @since 2020-03-23 23:47:10
 */
public interface CompanyNatureDao {

    /**
     * 通过ID查询单条数据
     *
     * @param code 主键
     * @return 实例对象
     */
    CompanyNature queryById(String code);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<CompanyNature> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param companyNature 实例对象
     * @return 对象列表
     */
    List<CompanyNature> queryAll(CompanyNature companyNature);

    /**
     * 新增数据
     *
     * @param companyNature 实例对象
     * @return 影响行数
     */
    int insert(CompanyNature companyNature);

    /**
     * 修改数据
     *
     * @param companyNature 实例对象
     * @return 影响行数
     */
    int update(CompanyNature companyNature);

    /**
     * 通过主键删除数据
     *
     * @param code 主键
     * @return 影响行数
     */
    int deleteById(String code);

}