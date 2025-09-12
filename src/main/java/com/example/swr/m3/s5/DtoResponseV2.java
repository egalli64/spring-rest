/*
 * Spring Boot Web REST tutorial 
 * 
 * https://github.com/egalli64/spring-rest
 */
package com.example.swr.m3.s5;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Coder DTO for request - version 2 (email added)
 */
public record DtoResponseV2(Integer id, String firstName, String lastName, LocalDate hireDate, BigDecimal salary,
        String email) {
    public DtoResponseV2(EntityV2 coder) {
        this(coder.getId(), coder.getFirstName(), coder.getLastName(), coder.getHireDate(), coder.getSalary(),
                coder.getEmail());
    }
}
