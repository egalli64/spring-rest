/*
 * A Spring Boot RESTful application 
 * 
 * https://github.com/egalli64/swr
 */
package com.example.swr.m1.s2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/m1/s2")
public class ClassicCtrl {
    private static final Logger log = LogManager.getLogger(RestCtrl.class);

    @GetMapping("/minimal")
    public String minimal() {
        log.traceEntry("minimal()");

        return "/m1/s2/minimal";
    }

    @GetMapping("/bean")
    public String bean(Model model) {
        log.traceEntry("bean()");
        model.addAttribute("bean", new HelloBean("Hello World!"));
        return "/m1/s2/bean";
    }
}
