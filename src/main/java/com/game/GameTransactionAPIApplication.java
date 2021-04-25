package com.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class GameTransactionAPIApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameTransactionAPIApplication.class, args);
    }
}
