package com.example.swr.s05;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.swr.dao.Coder;
import com.example.swr.dao.CoderRepo;

@RestController
public class CoderGetOr404Ctrl {
    private static final Logger log = LogManager.getLogger(CoderGetOr404Ctrl.class);

    private CoderRepo repo;

    public CoderGetOr404Ctrl(CoderRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/s05/coders/{id}")
    public Coder get(@PathVariable Integer id) {
        log.trace("get " + id);
        return repo.findById(id).orElseThrow( //
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Coder %d not found", id)));
    }

}
