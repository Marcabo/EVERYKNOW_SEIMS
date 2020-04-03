package com.herion.everyknow.seims.deploy;

import com.herion.everyknow.web.advice.ControllerAspect;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@Slf4j
@SpringBootApplication(scanBasePackages = {
        "com.herion.everyknow.seims.**"
})
public class EveryKnowSEIMSApplication {

    // 配置全局 入参 出参 拦截
    @Bean
    public ControllerAspect controllerAspect() {
        return new ControllerAspect();
    }


    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(EveryKnowSEIMSApplication.class);
        springApplication.run(args);
    }
}
