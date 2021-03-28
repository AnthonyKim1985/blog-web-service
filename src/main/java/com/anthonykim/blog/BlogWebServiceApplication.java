package com.anthonykim.blog;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableRabbit
@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
@RequiredArgsConstructor
@PropertySource(value = "classpath:environment.properties")
@EnableJpaRepositories(basePackages = "com.anthonykim.blog.persistence.mysql")
@EnableRedisRepositories(basePackages = "com.anthonykim.blog.persistence.redis")
public class BlogWebServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogWebServiceApplication.class, args);
    }
}
