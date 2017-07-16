package com.powerbridge.bssp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.powerbridge.bssp.*")
public class BsspReceiptApplication {

    public static void main(String[] args) {
        SpringApplication.run(BsspReceiptApplication.class, args);
    }
}
