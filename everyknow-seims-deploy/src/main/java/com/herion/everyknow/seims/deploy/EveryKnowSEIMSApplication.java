package com.herion.everyknow.seims.deploy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class EveryKnowSEIMSApplication {


    public static void main(String[] args) {
        log.debug("Spring Application Running...");
        SpringApplication springApplication = new SpringApplication(EveryKnowSEIMSApplication.class);
        springApplication.run(args);

    }
}
