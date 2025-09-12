/*
 * Spring Boot Web REST tutorial 
 * 
 * https://github.com/egalli64/spring-rest
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

import com.example.swr.exception.CoderNotFoundException;
import com.example.swr.m3.s5.DtoRequestV1;
import com.example.swr.m3.s5.EntityV2;
import com.example.swr.m3.s5.VersionedRepository;

import jakarta.validation.Valid;

/**
 * HATEOAS Controller for Coder V1 - client doesn't know anything about email
 */
@RestController
@RequestMapping("/api/v1/m3/s6/coders")
public class HateoasV1Controller {
    private static final String DEFAULT_EMAIL = "default@example.com";
    private static final Logger log = LogManager.getLogger(HateoasV1Controller.class);

    private final VersionedRepository repo;
    private final PagedResourcesAssembler<EntityV2> pagedAsm;
    private final AssemblerVersion1 coderAsm;

    public HateoasV1Controller(VersionedRepository repo, PagedResourcesAssembler<EntityV2> pagedAsm,
            AssemblerVersion1 coderAsm) {
        this.repo = repo;
        this.pagedAsm = pagedAsm;
        this.coderAsm = coderAsm;
    }

    /**
     * Get all coders paged and sorted
     * 
     * <pre>
         curl -v localhost:8080/api/v1/m3/s5/coders
     * </pre>
     */
    @GetMapping
    public ResponseEntity<PagedModel<HateoasDtoResponseV1>> getAll(
            @PageableDefault(sort = "lastName") Pageable pageable) {
        log.traceEntry("get all {}", pageable);

        Page<EntityV2> page = repo.findAll(pageable);
        return ResponseEntity.ok(pagedAsm.toModel(page, coderAsm));
    }

    /**
     * Get a coder by id
     * 
     * <pre>
         curl -v http://localhost:8080/api/v1/m3/s6/coders/105
     * </pre>
     */
    @GetMapping("/{id}")
    public ResponseEntity<HateoasDtoResponseV1> get(@PathVariable Integer id) {
        log.traceEntry("get({})", id);

        EntityV2 coder = repo.findById(id).orElseThrow(() -> new CoderNotFoundException(id));

        return ResponseEntity.ok(coderAsm.toModel(coder));
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
    public ResponseEntity<HateoasDtoResponseV1> create(@Valid @RequestBody DtoRequestV1 dto) {
        log.traceEntry("create({})", dto);

        EntityV2 coder = new EntityV2(dto.firstName(), dto.lastName(), dto.hireDate(), dto.salary(), DEFAULT_EMAIL);
        EntityV2 result = repo.save(coder);
        return ResponseEntity.created(location(result.getId())).body(new HateoasDtoResponseV1(result));
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
    public ResponseEntity<HateoasDtoResponseV1> update(@PathVariable Integer id, @Valid @RequestBody DtoRequestV1 dto) {
        log.traceEntry("update({}, {})", id, dto);

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
        return ResponseEntity.ok(new HateoasDtoResponseV1(result));
    }
}
