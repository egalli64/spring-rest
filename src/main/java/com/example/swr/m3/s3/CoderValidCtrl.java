/*
 * Spring Boot Web REST tutorial 
 * 
 * https://github.com/egalli64/spring-rest
 */
package com.example.swr.m3.s3;

import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.swr.model.Coder;
import com.example.swr.repository.CoderRepository;

import jakarta.validation.Valid;

/**
 * A controller focused to showing validation at work
 */
@RestController
@RequestMapping("/api/m3/s3/coders")
public class CoderValidCtrl {
    private static final Logger log = LogManager.getLogger(CoderValidCtrl.class);

    private CoderRepository repo;

    public CoderValidCtrl(CoderRepository repo) {
        this.repo = repo;
    }

    /**
     * <pre>
     * curl -i -X POST -H "Content-Type: application/json" -d "{\"firstName\":\"A\"}" localhost:8080/api/m3/s3/coders
     * </pre>
     */
    @PostMapping
    public ResponseEntity<Coder> create(@Valid @RequestBody Coder dto) {
        log.trace("create {}", dto);
        Coder savedCoder = repo.save(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedCoder.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedCoder);
    }

    /**
     * 
     * <pre>
     curl -i -X PUT -H "Content-Type: application/json" -d ^
         "{\"firstName\":\"T\"}" ^
         localhost:8080/api/m3/s3/coders/1
     * </pre>
     * 
     * Notice:
     * <ul>
     * <li>the @Valid annotation on the coder parameter, acting as input DTO</li>
     * <li>the CoderNotFoundException exception thrown if the coder is missing</li>
     * <li>the ResponseEntity use as return value</li>
     * </ul>
     */
    @PutMapping("/{id}")
    public ResponseEntity<Coder> update(@PathVariable Integer id, @Valid @RequestBody Coder dto) {
        log.trace("update {} {}", id, dto);

        Coder coder = repo.findById(id)
                .orElseThrow(() -> new CoderNotFoundException("Coder with ID " + id + " not found for update"));

        // copy fields from coder DTO in the coder JPA entity
        coder.setFirstName(dto.getFirstName());
        coder.setLastName(dto.getLastName());
        coder.setHireDate(dto.getHireDate());
        coder.setSalary(dto.getSalary());

        return ResponseEntity.ok(repo.save(coder));
    }
}
