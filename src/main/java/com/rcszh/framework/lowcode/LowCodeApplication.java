package com.rcszh.framework.lowcode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.rcszh.framework.lowcode.mapper")
@SpringBootApplication
public class LowCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(LowCodeApplication.class, args);
    }

}
