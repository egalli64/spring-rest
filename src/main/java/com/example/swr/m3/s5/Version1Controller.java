/*
 * Spring Boot Web REST tutorial 
 * 
 * https://github.com/egalli64/spring-rest
 */
package com.example.swr.m3.s5;

import java.net.URI;
import java.util.List;

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

import com.example.swr.exception.CoderNotFoundException;

import jakarta.validation.Valid;

/**
 * Controller for Coder V1 - client doesn't know about email
 */
@RestController
@RequestMapping("/api/v1/m3/s5/coders")
public class Version1Controller {
    private static final String DEFAULT_EMAIL = "default@example.com";
    private static final Logger log = LogManager.getLogger(Version1Controller.class);

    private VersionedRepository repo;

    public Version1Controller(VersionedRepository repo) {
        this.repo = repo;
    }

    /**
     * Get all coders (ignore email)
     * 
     * <pre>
         curl -v localhost:8080/api/v1/m3/s5/coders
     * </pre>
     */
    @GetMapping
    public ResponseEntity<List<DtoResponseV1>> getAll() {
        log.traceEntry("getAll()");

        List<DtoResponseV1> response = repo.findAll().stream().map(DtoResponseV1::new).toList();
        return ResponseEntity.ok(response);
    }

    /**
     * Create coder (defaulting email)
     * 
     * <pre>
         curl -i -X POST -H "Content-Type: application/json" -d ^
         "{\"firstName\":\"Tom\",\"lastName\":\"Smith\",\"hireDate\":\"2025-01-01\",\"salary\":\"7200.0\"}" ^
         localhost:8080/api/v1/m3/s5/coders
     * </pre>
     * 
     */
    @PostMapping
    public ResponseEntity<DtoResponseV1> create(@Valid @RequestBody DtoRequestV1 dto) {
        log.traceEntry("create({})", dto);

        var coder = new EntityV2(dto.firstName(), dto.lastName(), dto.hireDate(), dto.salary(), DEFAULT_EMAIL);
        EntityV2 result = repo.save(coder);
        return ResponseEntity.created(location(result.getId())).body(new DtoResponseV1(result));
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
         curl -i -X PUT -H "Content-Type: application/json" -d ^
         "{\"firstName\":\"TJ\",\"lastName\":\"Smith\",\"hireDate\":\"2025-01-01\",\"salary\":\"7200.0\"}" ^
         localhost:8080/api/v1/m3/s5/coders/1
     * </pre>
     */
    @PutMapping("/{id}")
    public ResponseEntity<DtoResponseV1> update(@PathVariable Integer id, @Valid @RequestBody DtoRequestV1 dto) {
        log.traceEntry("update({} {})", id, dto);

        EntityV2 coder = repo.findById(id).orElseThrow(() -> new CoderNotFoundException(id));

        coder.setFirstName(dto.firstName());
        coder.setLastName(dto.lastName());
        coder.setHireDate(dto.hireDate());
        coder.setSalary(dto.salary());
        // defensive programming - email not null should be already enforced by DBMS
        if (coder.getEmail() == null) {
            coder.setEmail("default@example.com");
        }

        EntityV2 result = repo.save(coder);
        return ResponseEntity.ok(new DtoResponseV1(result));
    }
}
