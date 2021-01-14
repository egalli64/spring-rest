package com.example.swr.s10;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.swr.s08.Coder;
import com.example.swr.s08.CoderRepo;

@RestController
public class CoderPropertyCtrl {
    private static final Logger log = LoggerFactory.getLogger(CoderPropertyCtrl.class);

    @Autowired
    private CoderRepo repo;

    @GetMapping("/s10p/coders")
    public List<Coder> getAll() {
        log.trace("getAll");
        return repo.findAll();
    }
}
