/*
 * A Spring Boot RESTful application 
 * 
 * https://github.com/egalli64/swr
 */
package com.example.swr.m3.s6;

import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
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
import com.example.swr.m3.s5.CoderRequestV1;
import com.example.swr.m3.s5.VersionedCoder;
import com.example.swr.m3.s5.VersionedCoderRepo;

import jakarta.validation.Valid;

/**
 * HATEOAS Controller for Coder V1 - client doesn't know anything about email
 */
@RestController
@RequestMapping("/api/v1/m3/s6/coders")
public class CoderV1HCtrl {
    private static final String DEFAULT_EMAIL = "default@example.com";
    private static final Logger log = LogManager.getLogger(CoderV1HCtrl.class);

    private final VersionedCoderRepo repo;
    private final PagedResourcesAssembler<VersionedCoder> pagedAsm;
    private final CoderV1Assembler coderAsm;

    public CoderV1HCtrl(VersionedCoderRepo repo, PagedResourcesAssembler<VersionedCoder> pagedAsm,
            CoderV1Assembler coderAsm) {
        this.repo = repo;
        this.pagedAsm = pagedAsm;
        this.coderAsm = coderAsm;
    }

    /**
     * Get all coders paged and sorted
     * 
     * <pre>
     * curl -v localhost:8080/api/v1/m3/s5/coders
     * </pre>
     */
    @GetMapping
    public ResponseEntity<PagedModel<CoderResponseV1H>> getAll(@PageableDefault(sort = "lastName") Pageable pageable) {
        log.traceEntry("get all {}", pageable);

        Page<VersionedCoder> page = repo.findAll(pageable);
        return ResponseEntity.ok(pagedAsm.toModel(page, coderAsm));
    }

    /**
     * Get a coder by id
     * 
     * <pre>
     * curl -v http://localhost:8080/api/v1/m3/s6/coders/105
     * </pre>
     */
    @GetMapping("/{id}")
    public ResponseEntity<CoderResponseV1H> get(@PathVariable Integer id) {
        log.traceEntry("get {}", id);

        VersionedCoder coder = repo.findById(id)
                .orElseThrow(() -> new CoderNotFoundException("Coder " + id + " not found"));

        return ResponseEntity.ok(coderAsm.toModel(coder));
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
    public ResponseEntity<CoderResponseV1H> create(@Valid @RequestBody CoderRequestV1 dto) {
        log.traceEntry("create {}", dto);

        var coder = new VersionedCoder(dto.firstName(), dto.lastName(), dto.hireDate(), dto.salary(), DEFAULT_EMAIL);
        VersionedCoder result = repo.save(coder);
        return ResponseEntity.created(location(result.getId())).body(new CoderResponseV1H(result));
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
    public ResponseEntity<CoderResponseV1H> update(@PathVariable Integer id, @Valid @RequestBody CoderRequestV1 dto) {
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
        return ResponseEntity.ok(new CoderResponseV1H(result));
    }
}
