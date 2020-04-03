package com.herion.everyknow.seims.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.herion.everyknow.seims.dao.entity.Clazz;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description ${description}
 * @auther wubo25320
 * @create 2020-04-02 13:30
 */
@Mapper
public interface ClazzMapper extends BaseMapper<Clazz> {

    int insertBatchSomeColumn(List<Clazz> entityList);
}