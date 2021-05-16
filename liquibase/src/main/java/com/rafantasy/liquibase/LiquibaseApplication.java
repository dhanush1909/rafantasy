package com.rafantasy.liquibase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@ComponentScan(basePackages = {""})
public class LiquibaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(LiquibaseApplication.class, args);
    }
}