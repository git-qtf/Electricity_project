package com.fh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.fh.*.mapper")
public class SpringbootUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootUserApplication.class, args);
    }

}
