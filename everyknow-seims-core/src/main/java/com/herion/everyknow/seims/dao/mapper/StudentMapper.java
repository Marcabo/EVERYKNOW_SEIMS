package com.herion.everyknow.seims.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.herion.everyknow.seims.dao.entity.Clazz;import com.herion.everyknow.seims.dao.entity.Student;import java.util.List;

/**
 * @Description ${description}
 * @auther wubo25320
 * @create 2020-04-05 17:56
 */
public interface StudentMapper extends BaseMapper<Student> {
    int insertBatchSomeColumn(List<Student> entityList);
}