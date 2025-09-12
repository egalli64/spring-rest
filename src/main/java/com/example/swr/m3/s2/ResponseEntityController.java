/*
 * Spring Boot Web REST tutorial 
 * 
 * https://github.com/egalli64/spring-rest
 */
package com.example.swr.m3.s2;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.swr.exception.CoderNotFoundException;
import com.example.swr.model.Coder;
import com.example.swr.repository.CoderRepository;

/**
 * A controller using ResponseEntity
 */
@RestController
@RequestMapping("/api/m3/s2/coders")
public class ResponseEntityController {
    private static final Logger log = LogManager.getLogger(ResponseEntityController.class);

    private CoderRepository repo;

    public ResponseEntityController(CoderRepository repo) {
        this.repo = repo;
    }

    /**
     * <pre>
         curl -v localhost:8080/api/m3/s2/coders
     * </pre>
     * 
     * @return a response with status HTTP 200 OK
     */
    @GetMapping
    public ResponseEntity<List<Coder>> getAllCoders() {
        log.traceEntry("getAll");
        return ResponseEntity.ok(repo.findAll());
    }

    /**
     * <pre>
         curl -i -X DELETE localhost:8080/api/m3/s2/coders/1
     * </pre>
     * 
     * @return a response with status HTTP 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoder(@PathVariable Integer id) {
        log.trace("delete " + id);

        if (!repo.existsById(id)) {
            throw new CoderNotFoundException(id);
        }
        repo.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
