/*
 * A Spring Boot RESTful application 
 * 
 * https://github.com/egalli64/swr
 */
package com.example.swr.m2.s6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.swr.dao.CoderRepo;

@RestController
@RequestMapping("/api/m2/s6")
public class CoderDeleteCtrl {
    private static final Logger log = LogManager.getLogger(CoderDeleteCtrl.class);

    private CoderRepo repo;

    public CoderDeleteCtrl(CoderRepo repo) {
        this.repo = repo;
    }

    /**
     * Assuming coder 1 has already been inserted - see POST in previous example
     * 
     * <pre>
     * curl -i -X DELETE localhost:8080/api/m2/s6/coders/1
     * </pre>
     */
    @DeleteMapping("/coders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        log.trace("delete " + id);

        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Coder %d not found", id));
        }
        repo.deleteById(id);
    }
}
