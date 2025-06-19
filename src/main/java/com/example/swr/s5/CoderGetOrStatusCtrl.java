package com.example.swr.s5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.swr.dao.Coder;
import com.example.swr.dao.CoderRepo;

@RestController
@RequestMapping("/s5")
public class CoderGetOrStatusCtrl {
    private static final Logger log = LogManager.getLogger(CoderGetOrStatusCtrl.class);

    private CoderRepo repo;

    public CoderGetOrStatusCtrl(CoderRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/coders/{id}")
    public Coder get(@PathVariable Integer id) {
        log.traceEntry("get " + id);
        return repo.findById(id).orElseThrow( //
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Coder %d not found", id)));
    }

    @GetMapping("/coders/mistake")
    public Coder mistake() {
        log.traceEntry("mistake");
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Something bad happened"));
    }
}
