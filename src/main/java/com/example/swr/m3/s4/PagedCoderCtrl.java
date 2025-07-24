/*
 * A Spring Boot RESTful application 
 * 
 * https://github.com/egalli64/swr
 */
package com.example.swr.m3.s4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.swr.dao.Coder;

/**
 * A paged/sorted controller
 */
@RestController
@RequestMapping("/api/m3/s4/coders")
public class PagedCoderCtrl {
    private static final Logger log = LogManager.getLogger(PagedCoderCtrl.class);

    private PagedCoderRepo repo;

    public PagedCoderCtrl(PagedCoderRepo repo) {
        this.repo = repo;
    }

    /**
     * Get all coders - paged and sorted - see defaults
     * 
     * <pre>
     * Default             : curl -v localhost:8080/api/m3/s4/coders
     * Given page and size : curl -v localhost:8080/api/m3/s4/coders?page=1&size=5
     * Given page and sort : curl -v localhost:8080/api/m3/s4/coders?page=1&sort=salary,desc
     * Multiple sort criteria: curl -v localhost:8080/api/m3/s4/coders?sort=firstName,asc&sort=lastName,desc
     * </pre>
     */
    @GetMapping
    public ResponseEntity<Page<Coder>> getAll(@PageableDefault(size = 6, sort = "lastName") Pageable pageable) {
        log.traceEntry("getAll");

        Page<Coder> page = repo.findAll(pageable);
        return ResponseEntity.ok(page);
    }
}
