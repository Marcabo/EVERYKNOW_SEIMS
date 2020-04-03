package com.herion.everyknow.seims.deploy;

import com.herion.everyknow.seims.dao.entity.Clazz;
import com.herion.everyknow.seims.dao.mapper.ClazzMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

/**
 * @Description 测试
 * @auther wubo25320
 * @create 2020-03-18 9:13
 */
@SpringBootTest
public class BaseTest {

    @Autowired
    private ClazzMapper clazzMapper;

    @Test
    void Test() {
        System.out.println("ceshi");
    }

    /**
     * 测试 mybatis-plus 批量插入选装件
     */
    @Test
    void batchInsert() {
        ArrayList<Clazz> clazzList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Clazz clazz = new Clazz();
            clazz.setClazzName("测试" + i + "班");
            clazz.setCollegeCode("test");
            clazz.setDeptCode("test");
            clazzList.add(clazz);
        }
        clazzMapper.insertBatchSomeColumn(clazzList);
    }
}
