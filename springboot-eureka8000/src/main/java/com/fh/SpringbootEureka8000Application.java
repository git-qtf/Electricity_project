package com.fh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpringbootEureka8000Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootEureka8000Application.class, args);
    }

}
