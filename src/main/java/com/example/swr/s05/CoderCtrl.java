package com.example.swr.s05;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.swr.s04.Coder;
import com.example.swr.s04.CoderRepo;

@RestController
public class CoderCtrl {
    private static final Logger log = LoggerFactory.getLogger(CoderCtrl.class);

    private CoderRepo repo;

    public CoderCtrl(CoderRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/s05/coders")
    public List<Coder> getAll() {
        log.trace("getAll");
        return repo.findAll();
    }
}
