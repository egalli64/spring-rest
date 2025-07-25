/*
 * A Spring Boot RESTful application 
 * 
 * https://github.com/egalli64/swr
 */
package com.example.swr.m3.s5;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Coder DTO for request - version 2 (email added)
 */
public record CoderResponseV2(Integer id, String firstName, String lastName, LocalDate hireDate, BigDecimal salary,
        String email) {
    public CoderResponseV2(VersionedCoder coder) {
        this(coder.getId(), coder.getFirstName(), coder.getLastName(), coder.getHireDate(), coder.getSalary(),
                coder.getEmail());
    }
}
