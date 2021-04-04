package com.example.swr.s03;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.swr.dao.Coder;
import com.example.swr.dao.CoderRepo;

@RestController
@RequestMapping("/s03")
public class CoderCtrl {
    private static final Logger log = LogManager.getLogger(CoderCtrl.class);

    private CoderRepo repo;

    public CoderCtrl(CoderRepo repo) {
        this.repo = repo;
    }

    @GetMapping("coders")
    public Iterable<Coder> getAll() {
        log.traceEntry("getAll");
        return repo.findAll();
    }

    @GetMapping("mistake")
    public Iterable<Coder> mistake() {
        log.traceEntry("mistake");
        throw new IllegalStateException("Something bad happened");
    }
}
