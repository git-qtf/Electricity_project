package com.fh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.fh.*.mapper")
public class SpringbootCategoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCategoryApplication.class, args);
    }

}
