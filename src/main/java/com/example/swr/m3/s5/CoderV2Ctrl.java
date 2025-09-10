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
 * Controller for Coder V2 - client knows about email
 */
@RestController
@RequestMapping("/api/v2/m3/s5/coders")
public class CoderV2Ctrl {
    private static final Logger log = LogManager.getLogger(CoderV2Ctrl.class);

    private VersionedCoderRepo repo;

    public CoderV2Ctrl(VersionedCoderRepo repo) {
        this.repo = repo;
    }

    /**
     * Get all coders
     * 
     * <pre>
     * curl -v localhost:8080/api/v2/m3/s5/coders
     * </pre>
     */
    @GetMapping
    public ResponseEntity<List<CoderResponseV2>> getAll() {
        log.traceEntry("get all");

        var coders = repo.findAll();
        var response = StreamSupport.stream(coders.spliterator(), false).map(CoderResponseV2::new).toList();
        return ResponseEntity.ok(response);
    }

    /**
     * Create coder
     * 
     * <pre>
     * curl -i -X POST -H "Content-Type: application/json" -d ^
         "{\"firstName\":\"Tom\",\"lastName\":\"Smith\",\"hireDate\":\"2025-01-01\",\"salary\":\"7200.0\",\"email\":\"ts@example.com\"}" ^
         localhost:8080/api/v2/m3/s5/coders
     * </pre>
     */
    @PostMapping
    public ResponseEntity<CoderResponseV2> create(@Valid @RequestBody CoderRequestV2 dto) {
        log.traceEntry("create {}", dto);

        var coder = new VersionedCoder(dto.firstName(), dto.lastName(), dto.hireDate(), dto.salary(), dto.email());

        VersionedCoder result = repo.save(coder);
        CoderResponseV2 response = new CoderResponseV2(result);
        return ResponseEntity.created(location(response.id())).body(response);
    }

    /**
     * Generate the URI location for the coder with the passed id
     */
    private URI location(Integer id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    /**
     * Update coder
     * 
     * <pre>
     * curl -i -X PUT -H "Content-Type: application/json" -d ^
         "{\"firstName\":\"TJ\",\"lastName\":\"Smith\",\"hireDate\":\"2025-01-01\",\"salary\":\"7200.0\",\"email\":\"ts@example.com\"}" ^
         localhost:8080/api/v2/m3/s5/coders/1
     * </pre>
     */
    @PutMapping("/{id}")
    public ResponseEntity<CoderResponseV2> update(@PathVariable Integer id, @Valid @RequestBody CoderRequestV2 dto) {
        log.traceEntry("update {} {}", id, dto);

        VersionedCoder coder = repo.findById(id)
                .orElseThrow(() -> new CoderNotFoundException("Coder " + id + " not found"));

        coder.setFirstName(dto.firstName());
        coder.setLastName(dto.lastName());
        coder.setHireDate(dto.hireDate());
        coder.setSalary(dto.salary());
        coder.setEmail(dto.email());

        var result = repo.save(coder);
        return ResponseEntity.ok(new CoderResponseV2(result));
    }
}
