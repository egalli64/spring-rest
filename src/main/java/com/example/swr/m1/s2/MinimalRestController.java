/*
 * Spring Boot Web REST tutorial 
 * 
 * https://github.com/egalli64/spring-rest
 */
package com.example.swr.m1.s2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.swr.model.Hello;

/**
 * A Rest Controller accepting also CORS from the specified URL
 */
@RestController
@RequestMapping("/api/m1/s2")
@CrossOrigin(origins = "http://localhost:4200")
public class MinimalRestController {
    private static final Logger log = LogManager.getLogger(MinimalRestController.class);

    @GetMapping("/answer")
    int answer() {
        log.traceEntry("answer()");
        return 42;
    }

    @GetMapping("/bean")
    Hello bean() {
        log.traceEntry("bean()");
        return new Hello("Hello World!");
    }
}
