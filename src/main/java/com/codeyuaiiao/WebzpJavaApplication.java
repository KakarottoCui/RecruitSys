package com.codeyuaiiao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.codeyuaiiao.mapper")
public class WebzpJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebzpJavaApplication.class, args);
    }

}
