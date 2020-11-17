package com.fh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpringbootEureka8001Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootEureka8001Application.class, args);
    }

}
