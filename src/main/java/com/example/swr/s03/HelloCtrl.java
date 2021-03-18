package com.example.swr.s03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HelloCtrl {
    private static final Logger log = LoggerFactory.getLogger(HelloCtrl.class);

    @GetMapping("/hello")
    Hello sayHello() {
        log.trace("hello");
        return new Hello("Hello World!");
    }
}
