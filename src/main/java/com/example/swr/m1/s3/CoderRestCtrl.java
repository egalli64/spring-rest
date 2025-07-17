/*
 * A Spring Boot RESTful application 
 * 
 * https://github.com/egalli64/swr
 */
package com.example.swr.m1.s3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.swr.dao.Coder;
import com.example.swr.dao.CoderRepo;

@RestController
@RequestMapping("/api/m1/s3")
public class CoderRestCtrl {
    private static final Logger log = LogManager.getLogger(CoderRestCtrl.class);

    private CoderRepo repo;

    public CoderRestCtrl(CoderRepo repo) {
        this.repo = repo;
    }

    /**
     * <pre>
        curl -v localhost:8080/api/m1/s3/coders/107
     * </pre>
     */
// @GetMapping (Post, Put, ...) are aliases of @RequestMapping
//    @RequestMapping(method = RequestMethod.GET, path = "/coders/{id}")
    @GetMapping("/coders/{id}")
    public Coder get(@PathVariable Integer id) {
        log.traceEntry("get " + id);

        // !! Unsatisfactory approach: status 404 would be more meaningful !!
        return repo.findById(id).orElseThrow();
    }

    /**
     * <pre>
        curl -X POST -H "Content-Type: application/json" -d^
          "{\"firstName\":\"Tom\",\"lastName\":\"Lee\",\"hireDate\":\"2025-01-01\",\"salary\":\"7200.0\"}"^
          localhost:8080/api/m1/s3/coders
     * </pre>
     */
    @PostMapping("/coders")
    public Coder create(@RequestBody Coder coder) {
        log.traceEntry("create " + coder);
        return repo.save(coder);
    }

    /**
     * <pre>
        curl -v localhost:8080/api/m1/s3/crash
     * </pre>
     */
    @GetMapping("crash")
    public Iterable<Coder> crash() {
        log.traceEntry("crash");
        throw new IllegalStateException("Something bad happened");
    }
}
