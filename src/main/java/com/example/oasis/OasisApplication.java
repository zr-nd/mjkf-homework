package com.example.oasis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
//@EnableCaching
public class OasisApplication {

    public static void main(String[] args) {
        SpringApplication.run(OasisApplication.class, args);
    }
}
