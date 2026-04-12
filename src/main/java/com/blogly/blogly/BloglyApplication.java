package com.blogly.blogly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class BloglyApplication {

    static void main(String[] args) {
        SpringApplication.run(BloglyApplication.class, args);
    }

}
