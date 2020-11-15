package com.fh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fh.*.mapper")
public class SpringbootProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootProductApplication.class, args);
    }

}
