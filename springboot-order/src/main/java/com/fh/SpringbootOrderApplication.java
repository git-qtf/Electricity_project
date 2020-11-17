package com.fh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.fh.*.mapper")
public class SpringbootOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootOrderApplication.class, args);
    }

}
