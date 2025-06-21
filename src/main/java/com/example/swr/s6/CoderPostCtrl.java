package com.example.swr.s6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.swr.dao.Coder;
import com.example.swr.dao.CoderRepo;

@RestController
@RequestMapping("/s6")
public class CoderPostCtrl {
    private static final Logger log = LogManager.getLogger(CoderPostCtrl.class);

    private CoderRepo repo;

    public CoderPostCtrl(CoderRepo repo) {
        this.repo = repo;
    }

    @PostMapping("/coders")
    public Coder create(@RequestBody Coder coder) {
        log.traceEntry("create " + coder);
        return repo.save(coder);
    }
}
