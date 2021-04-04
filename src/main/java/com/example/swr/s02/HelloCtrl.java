package com.example.swr.s02;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/s02")
@CrossOrigin(origins = "http://localhost:4200")
public class HelloCtrl {
    private static final Logger log = LogManager.getLogger(HelloCtrl.class);

    @GetMapping("/answer")
    int answer() {
        log.traceEntry("answer");
        return 42;
    }

    @GetMapping("/hello")
    Hello sayHello() {
        log.traceEntry("sayHello");
        return new Hello("Hello World!");
    }
}
