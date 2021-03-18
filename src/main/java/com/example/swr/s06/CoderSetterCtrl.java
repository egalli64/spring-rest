package com.example.swr.s06;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.swr.s04.Coder;
import com.example.swr.s04.CoderRepo;

@RestController
public class CoderSetterCtrl {
    private static final Logger log = LoggerFactory.getLogger(CoderSetterCtrl.class);

    private CoderRepo repo;

    @Autowired
    public void setRepo(CoderRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/s06s/coders")
    public List<Coder> getAll() {
        log.trace("getAll");
        return repo.findAll();
    }
}
