package com.example.swr.s09;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.swr.s04.Coder;
import com.example.swr.s04.CoderRepo;

@RestController
public class CoderPostCtrl {
    private static final Logger log = LoggerFactory.getLogger(CoderPostCtrl.class);

    private CoderRepo repo;

    public CoderPostCtrl(CoderRepo repo) {
        this.repo = repo;
    }

    @PostMapping("/s09/coders")
    public Coder create(@RequestBody Coder coder) {
        log.trace("create " + coder);
        return repo.save(coder);
    }
}
