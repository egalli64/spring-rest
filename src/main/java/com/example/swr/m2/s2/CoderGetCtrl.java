/*
 * Spring Boot Web REST tutorial 
 * 
 * https://github.com/egalli64/spring-rest
 */
package com.example.swr.m2.s2;

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
@RequestMapping("/api/m2/s2")
public class CoderGetCtrl {
    private static final Logger log = LogManager.getLogger(CoderGetCtrl.class);

    private CoderRepository repo;

    public CoderGetCtrl(CoderRepository repo) {
        this.repo = repo;
    }

    /**
     * <pre>
        curl -v localhost:8080/api/m2/s2/coders
     * </pre>
     */
    @GetMapping("/coders")
    public Iterable<Coder> getAll() {
        log.traceEntry("getAll");
        return repo.findAll();
    }

    /**
     * <pre>
        curl -v localhost:8080/api/m2/s2/coders/107
     * </pre>
     */
    @GetMapping("/coders/{id}")
    public Coder get(@PathVariable Integer id) {
        log.traceEntry("get " + id);

        return repo.findById(id).orElseThrow( //
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Coder %d not found", id)));
    }
}
