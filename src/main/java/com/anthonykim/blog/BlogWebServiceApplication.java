package com.anthonykim.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:environment.properties")
public class BlogWebServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogWebServiceApplication.class, args);
    }
}
