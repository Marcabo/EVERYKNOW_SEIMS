package com.herion.everyknow.seims.dao;

import com.herion.everyknow.seims.dao.entity.Province;
import java.util.List;

/**
 * 省份代码表(Province)表数据库访问层
 *
 * @author wubo25320
 * @since 2020-03-24 13:51:39
 */
public interface ProvinceDao {

    /**
     * 通过ID查询单条数据
     *
     * @param code 主键
     * @return 实例对象
     */
    Province queryById(String code);

    /**
     * 通过省份名称查询省份
     * @param provinceName
     * @return
     */
    Province queryByName(String provinceName);

    /**
     * 通过实体作为筛选条件查询
     *
     * @return 对象列表
     */
    List<Province> queryAll();

}