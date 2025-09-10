/*
 * Spring Boot Web REST tutorial 
 * 
 * https://github.com/egalli64/spring-rest
 */
package com.example.swr.m1.s2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.swr.model.Hello;

@Controller
@RequestMapping("/m1/s2")
public class MinimalClassicController {
    private static final Logger log = LogManager.getLogger(MinimalRestController.class);

    @GetMapping("/minimal")
    public String minimal() {
        log.traceEntry("minimal()");

        return "/m1/s2/minimal";
    }

    @GetMapping("/bean")
    public String bean(Model model) {
        log.traceEntry("bean()");
        model.addAttribute("bean", new Hello("Hello World!"));
        return "/m1/s2/bean";
    }
}
