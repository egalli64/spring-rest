/*
 * Spring Boot Web REST tutorial 
 * 
 * https://github.com/egalli64/spring-rest
 */
package com.example.swr.m3.s5;

import java.net.URI;
import java.util.List;
import java.util.stream.StreamSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.swr.m3.s3.CoderNotFoundException;

import jakarta.validation.Valid;

/**
 * Controller for Coder V1 - client doesn't know about email
 */
@RestController
@RequestMapping("/api/v1/m3/s5/coders")
public class CoderV1Ctrl {
    private static final String DEFAULT_EMAIL = "default@example.com";
    private static final Logger log = LogManager.getLogger(CoderV1Ctrl.class);

    private VersionedCoderRepo repo;

    public CoderV1Ctrl(VersionedCoderRepo repo) {
        this.repo = repo;
    }

    /**
     * Get all coders (ignore email)
     * 
     * <pre>
     * curl -v localhost:8080/api/v1/m3/s5/coders
     * </pre>
     */
    @GetMapping
    public ResponseEntity<List<CoderResponseV1>> getAll() {
        log.traceEntry("get all");

        var coders = repo.findAll();
        var response = StreamSupport.stream(coders.spliterator(), false).map(CoderResponseV1::new).toList();
        return ResponseEntity.ok(response);
    }

    /**
     * Create coder (defaulting email)
     * 
     * <pre>
     * curl -i -X POST -H "Content-Type: application/json" -d ^
         "{\"firstName\":\"Tom\",\"lastName\":\"Smith\",\"hireDate\":\"2025-01-01\",\"salary\":\"7200.0\"}" ^
         localhost:8080/api/v1/m3/s5/coders
     * </pre>
     * 
     */
    @PostMapping
    public ResponseEntity<CoderResponseV1> create(@Valid @RequestBody CoderRequestV1 dto) {
        log.traceEntry("create {}", dto);

        var coder = new VersionedCoder(dto.firstName(), dto.lastName(), dto.hireDate(), dto.salary(), DEFAULT_EMAIL);
        VersionedCoder result = repo.save(coder);
        return ResponseEntity.created(location(result.getId())).body(new CoderResponseV1(result));
    }

    /**
     * Generate the URI location for the coder with the passed id
     */
    private URI location(Integer id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    /**
     * Update coder (ignoring email)
     * 
     * <pre>
     * curl -i -X PUT -H "Content-Type: application/json" -d ^
         "{\"firstName\":\"TJ\",\"lastName\":\"Smith\",\"hireDate\":\"2025-01-01\",\"salary\":\"7200.0\"}" ^
         localhost:8080/api/v1/m3/s5/coders/1
     * </pre>
     */
    @PutMapping("/{id}")
    public ResponseEntity<CoderResponseV1> update(@PathVariable Integer id, @Valid @RequestBody CoderRequestV1 dto) {
        log.traceEntry("update {} {}", id, dto);

        VersionedCoder coder = repo.findById(id)
                .orElseThrow(() -> new CoderNotFoundException("Coder " + id + " not found"));

        coder.setFirstName(dto.firstName());
        coder.setLastName(dto.lastName());
        coder.setHireDate(dto.hireDate());
        coder.setSalary(dto.salary());
        // defensive programming - email not null should be already enforced by DBMS
        if (coder.getEmail() == null) {
            coder.setEmail("default@example.com");
        }

        VersionedCoder result = repo.save(coder);
        return ResponseEntity.ok(new CoderResponseV1(result));
    }
}
