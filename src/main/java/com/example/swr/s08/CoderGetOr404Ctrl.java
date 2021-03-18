package com.example.swr.s08;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.swr.s04.Coder;
import com.example.swr.s04.CoderRepo;

@RestController
public class CoderGetOr404Ctrl {
    private static final Logger log = LoggerFactory.getLogger(CoderGetOr404Ctrl.class);

    private CoderRepo repo;

    public CoderGetOr404Ctrl(CoderRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/s08/coders/{id}")
    public Coder get(@PathVariable Integer id) {
        log.trace("get " + id);
        return repo.findById(id).orElseThrow( //
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Coder %d not found", id)));
    }

}
