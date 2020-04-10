package com.herion.everyknow.seims.dao;

import com.herion.everyknow.seims.dao.entity.Location;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 地区代码表(Location)表数据库访问层
 *
 * @author wubo25320
 * @since 2020-03-23 23:47:10
 */
public interface LocationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param code 主键
     * @return 实例对象
     */
    Location queryById(String code);

    /**
     * 通过实体作为筛选条件查询
     *
     * @return 对象列表
     */
    List<Location> queryAll();

    /**
     * 通过省份代码查询省份内所有城市
     * @param provinceCode
     * @return
     */
    List<Location> queryProvince(String provinceCode);

    /**
     * 通过城市名称查询 城市
     * @param cityName
     * @return
     */
    List<Location> queryByCityName(String cityName);
}