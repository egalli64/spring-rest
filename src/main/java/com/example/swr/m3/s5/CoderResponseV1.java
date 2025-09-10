/*
 * Spring Boot Web REST tutorial 
 * 
 * https://github.com/egalli64/spring-rest
 */
package com.example.swr.m3.s5;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Coder DTO for response - version 1 (no email)
 */
public record CoderResponseV1(Integer id, String firstName, String lastName, LocalDate hireDate, BigDecimal salary) {
    public CoderResponseV1(VersionedCoder coder) {
        this(coder.getId(), coder.getFirstName(), coder.getLastName(), coder.getHireDate(), coder.getSalary());
    }
}
