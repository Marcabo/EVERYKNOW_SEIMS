package com.herion.everyknow.seims.dao;

import com.herion.everyknow.seims.dao.entity.EmployMethod;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 就业方式表(此表不可修改)(EmployMethod)表数据库访问层
 *
 * @author wubo25320
 * @since 2020-03-23 23:47:10
 */
public interface EmployMethodDao {

    /**
     * 通过ID查询单条数据
     *
     * @param code 主键
     * @return 实例对象
     */
    EmployMethod queryById(String code);

    /**
     * 通过实体作为筛选条件查询
     *
     * @return 对象列表
     */
    List<EmployMethod> queryAll();
}