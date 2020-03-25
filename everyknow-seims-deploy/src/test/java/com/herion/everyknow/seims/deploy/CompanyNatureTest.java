package com.herion.everyknow.seims.deploy;

import com.herion.everyknow.seims.dao.CompanyNatureDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description 测试
 * @auther wubo25320
 * @create 2020-03-25 16:17
 */
@SpringBootTest
public class CompanyNatureTest {

    @Autowired
    private CompanyNatureDao companyNatureDao;

    @Test
    void test01() {
        companyNatureDao.queryById("force_unit");
    }
}
