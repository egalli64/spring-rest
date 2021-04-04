package com.example.swr.zed;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.swr.dao.Coder;
import com.example.swr.dao.CoderRepo;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CoderController {
    private static final Logger log = LogManager.getLogger(CoderController.class);

    private CoderRepo repo;

    public CoderController(CoderRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/coders")
    public Iterable<Coder> getAll() {
        log.trace("getAll");
        return repo.findAll();
    }

    @GetMapping("/coders/{id}")
    public Coder get(@PathVariable Integer id) {
        log.trace("get " + id);
        return repo.findById(id).orElseThrow(() -> new SwrNotFoundException(id));
    }

    @PostMapping("/coders")
    public Coder create(@RequestBody Coder coder) {
        log.trace("create " + coder);
        return repo.save(coder);
    }

    @PutMapping("/coders/{id}")
    public Coder update(@RequestBody Coder newer, @PathVariable Integer id) {
        log.trace(String.format("update coder %d by %s", id, newer));
        return repo.findById(id).map(coder -> {
            coder.setFirstName(newer.getFirstName());
            coder.setLastName(newer.getLastName());
            coder.setHireDate(newer.getHireDate());
            coder.setSalary(newer.getSalary());
            return repo.save(coder);
        }).orElseThrow(() -> new SwrNotFoundException(id));
    }

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
            if (newer.getSalary() != 0.0) {
                coder.setSalary(newer.getSalary());
            }
            return repo.save(coder);
        }).orElseThrow(() -> new SwrNotFoundException(id));
    }

    @DeleteMapping("/coders/{id}")
    public void delete(@PathVariable Integer id) {
        log.trace("delete " + id);

        try {
            repo.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new SwrNotFoundException(id, ex);
        }
    }
}
