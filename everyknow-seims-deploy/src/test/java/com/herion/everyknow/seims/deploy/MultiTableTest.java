package com.herion.everyknow.seims.deploy;

import com.herion.everyknow.seims.dao.StudentDao;
import com.herion.everyknow.seims.dao.entity.Student;
import com.herion.everyknow.seims.service.DataVisualService;
import com.herion.everyknow.seims.service.bean.DataVisualRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description 多表联查 测试
 * @auther wubo25320
 * @create 2020-04-24 14:14
 */
@SpringBootTest
public class MultiTableTest {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private DataVisualService dataVisualService;

    @Test
    public void test01() {
        Student student = new Student();
        student.setCollegeCode("rjxy");
        student.setDeptCode("rjgc");
        System.out.println(studentDao.getStudentAndEmploy(student));

        System.out.println("=============");
        System.out.println(dataVisualService.getByCondition(new DataVisualRequest()));
    }
}
