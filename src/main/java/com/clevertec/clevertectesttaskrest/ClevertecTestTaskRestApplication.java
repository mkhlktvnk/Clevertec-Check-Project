package com.clevertec.clevertectesttaskrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class ClevertecTestTaskRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClevertecTestTaskRestApplication.class, args);
    }
}
