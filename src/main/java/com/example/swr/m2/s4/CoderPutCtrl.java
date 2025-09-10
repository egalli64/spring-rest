/*
 * Spring Boot Web REST tutorial 
 * 
 * https://github.com/egalli64/spring-rest
 */
package com.example.swr.m2.s4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.swr.model.Coder;
import com.example.swr.repository.CoderRepository;

@RestController
@RequestMapping("/api/m2/s4")
public class CoderPutCtrl {
    private static final Logger log = LogManager.getLogger(CoderPutCtrl.class);

    private CoderRepository repo;

    public CoderPutCtrl(CoderRepository repo) {
        this.repo = repo;
    }

    /**
     * <pre>
        Assuming coder 1 has already been inserted - see POST in previous example
        curl -i -X PUT -H "Content-Type: application/json" -d ^
         "{\"firstName\":\"Tommy\",\"lastName\":\"Smith\",\"hireDate\":\"2025-01-01\",\"salary\":\"7200.0\"}" ^
         localhost:8080/api/m2/s4/coders/1
     * </pre>
     */
    @PutMapping("/coders/{id}")
    public Coder update(@RequestBody Coder newer, @PathVariable Integer id) {
        log.trace(String.format("update coder %d by %s", id, newer));
        return repo.findById(id).map(coder -> {
            coder.setFirstName(newer.getFirstName());
            coder.setLastName(newer.getLastName());
            coder.setHireDate(newer.getHireDate());
            coder.setSalary(newer.getSalary());
            return repo.save(coder);
        }).orElseThrow( //
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Coder %d not found", id)));
    }
}
