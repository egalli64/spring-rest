/*
 * A Spring Boot RESTful application 
 * 
 * https://github.com/egalli64/swr
 */
package com.example.swr.m2.s3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.swr.dao.Coder;
import com.example.swr.dao.CoderRepo;

@RestController
@RequestMapping("/api/m2/s3")
public class CoderPostCtrl {
    private static final Logger log = LogManager.getLogger(CoderPostCtrl.class);

    private CoderRepo repo;

    public CoderPostCtrl(CoderRepo repo) {
        this.repo = repo;
    }

    /**
     * <pre>
        curl -i -X POST -H "Content-Type: application/json" -d ^
         "{\"firstName\":\"Tom\",\"lastName\":\"Smith\",\"hireDate\":\"2025-01-01\",\"salary\":\"7200.0\"}" ^
         localhost:8080/api/m2/s3/coders
     * </pre>
     */
    @PostMapping("/coders")
    @ResponseStatus(HttpStatus.CREATED)
    public Coder create(@RequestBody Coder coder) {
        log.traceEntry("create " + coder);

        if (repo.existsByFirstNameAndLastName(coder.getFirstName(), coder.getLastName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Coder with this name already exists");
        }

        return repo.save(coder);
    }
}
