package com.rafantasy.liquibase;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppShutdownRunner implements CommandLineRunner {

    private final ConfigurableApplicationContext context;

    @Override
    public void run(String... args) throws Exception {
        System.exit(SpringApplication.exit(context));
    }
}
