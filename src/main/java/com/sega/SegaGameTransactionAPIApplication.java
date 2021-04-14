package com.sega;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SegaGameTransactionAPIApplication {

    public static void main(String[] args) {
        SpringApplication.run(SegaGameTransactionAPIApplication.class, args);
    }
}
