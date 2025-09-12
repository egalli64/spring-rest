/*
 * Spring Boot Web REST tutorial 
 * 
 * https://github.com/egalli64/spring-rest
 */
package com.example.swr.m2.s5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.swr.exception.CoderNotFoundException;
import com.example.swr.model.Coder;
import com.example.swr.repository.CoderRepository;

@RestController
@RequestMapping("/api/m2/s5")
public class PatchController {
    private static final Logger log = LogManager.getLogger(PatchController.class);

    private CoderRepository repo;

    public PatchController(CoderRepository repo) {
        this.repo = repo;
    }

    /**
     * Assuming coder 1 has already been inserted - see POST in previous example
     * 
     * <pre>
        curl -i -X PATCH -H "Content-Type: application/json" -d ^
         "{\"firstName\":\"Bob\"}" ^
         localhost:8080/api/m2/s5/coders/1
     * </pre>
     */
    @PatchMapping("/coders/{id}")
    public Coder partialUpdate(@RequestBody Coder newer, @PathVariable Integer id) {
        log.trace(String.format("partialUpdate coder %d by %s", id, newer));
        return repo.findById(id).map(coder -> {
            if (newer.getFirstName() != null) {
                coder.setFirstName(newer.getFirstName());
            }
            if (newer.getLastName() != null) {
                coder.setLastName(newer.getLastName());
            }
            if (newer.getHireDate() != null) {
                coder.setHireDate(newer.getHireDate());
            }
            if (newer.getSalary() != null) {
                coder.setSalary(newer.getSalary());
            }
            return repo.save(coder);
        }).orElseThrow(() -> new CoderNotFoundException(id));
    }
}
