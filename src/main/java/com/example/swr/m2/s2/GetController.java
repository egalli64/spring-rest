/*
 * Spring Boot Web REST tutorial 
 * 
 * https://github.com/egalli64/spring-rest
 */
package com.example.swr.m2.s2;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.swr.exception.CoderNotFoundException;
import com.example.swr.model.Coder;
import com.example.swr.repository.CoderRepository;

@RestController
@RequestMapping("/api/m2/s2")
public class GetController {
    private static final Logger log = LogManager.getLogger(GetController.class);

    private CoderRepository repo;

    public GetController(CoderRepository repo) {
        this.repo = repo;
    }

    /**
     * <pre>
        curl -v localhost:8080/api/m2/s2/coders
     * </pre>
     */
    @GetMapping("/coders")
    public List<Coder> getAll() {
        log.traceEntry("getAll");
        return repo.findAll();
    }

    /**
     * Found: 200, not found: 404
     * 
     * <pre>
        curl -v localhost:8080/api/m2/s2/coders/107
        
        curl -v localhost:8080/api/m2/s2/coders/99
     * </pre>
     */
    @GetMapping("/coders/{id}")
    public Coder get(@PathVariable Integer id) {
        log.traceEntry("get " + id);

        return repo.findById(id).orElseThrow(() -> new CoderNotFoundException(id));
    }
}
