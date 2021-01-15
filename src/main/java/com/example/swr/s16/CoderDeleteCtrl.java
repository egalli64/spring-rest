package com.example.swr.s16;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.swr.s08.CoderRepo;

@RestController
public class CoderDeleteCtrl {
    private static final Logger log = LoggerFactory.getLogger(CoderDeleteCtrl.class);

    private CoderRepo repo;

    public CoderDeleteCtrl(CoderRepo repo) {
        this.repo = repo;
    }

    @DeleteMapping("/s16/coders/{id}")
    public void delete(@PathVariable Integer id) {
        log.trace("delete " + id);
        try {
            repo.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Coder %d not found", id), ex);
        }
    }
}
