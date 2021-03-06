package com.a.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.a")
@EnableJpaRepositories(basePackages = "com.a.repository")
@EntityScan("com.a.entity")
public class StoreApplication {
  public static void main(String... args) {
    SpringApplication.run(StoreApplication.class, args);
  }
}