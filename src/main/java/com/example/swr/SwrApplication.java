/*
 * Spring Boot Web REST tutorial 
 * 
 * https://github.com/egalli64/spring-rest
 */
package com.example.swr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class SwrApplication {
    public static void main(String[] args) {
        SpringApplication.run(SwrApplication.class, args);
    }
}
