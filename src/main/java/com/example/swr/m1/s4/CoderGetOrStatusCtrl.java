/*
 * Spring Boot Web REST tutorial 
 * 
 * https://github.com/egalli64/spring-rest
 */
package com.example.swr.m1.s4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.swr.model.Coder;
import com.example.swr.repository.CoderRepository;

@RestController
@RequestMapping("/api/m1/s4")
public class CoderGetOrStatusCtrl {
    private static final Logger log = LogManager.getLogger(CoderGetOrStatusCtrl.class);

    private final CoderRepository repo;
    private final DisappointingService svc;

    public CoderGetOrStatusCtrl(CoderRepository repo, DisappointingService svc) {
        this.repo = repo;
        this.svc = svc;
    }

    @GetMapping("/coders/{id}")
    public Coder get(@PathVariable Integer id) {
        log.traceEntry("get " + id);
        return repo.findById(id).orElseThrow( //
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Coder %d not found", id)));
    }

    @GetMapping("/coders/crash")
    public void crash() {
        log.traceEntry("crash");
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something bad happened");
    }

    @GetMapping("/coders/crash2")
    public void crash2() {
        log.traceEntry("crash2");

        try {
            svc.alwaysFailing();
        } catch (IllegalStateException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something bad happened", ex);
        }
    }
}
