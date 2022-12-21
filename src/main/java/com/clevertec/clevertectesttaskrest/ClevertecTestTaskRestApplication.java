package com.clevertec.clevertectesttaskrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class ClevertecTestTaskRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClevertecTestTaskRestApplication.class, args);
    }

}
