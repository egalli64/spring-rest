/*
 * Spring Boot Web REST tutorial 
 * 
 * https://github.com/egalli64/spring-rest
 */
package com.example.swr.m3.s5;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Coder DTO for request - version 2 (email added)
 */
public record DtoRequestV2( //
        @Size(min = 2, max = 20, message = "First name must be between 2 and 20 characters") //
        @Pattern(regexp = "^[A-Za-z ]*$", message = "First name must contain only letters and spaces") //
        String firstName,

        @NotBlank(message = "Last name is mandatory") //
        @Size(min = 2, max = 25, message = "Last name must be between 2 and 25 characters") //
        @Pattern(regexp = "^[A-Za-z ]*$", message = "Last name must contain only letters and spaces") //
        String lastName,

        @NotNull(message = "Hire date is mandatory") //
        @PastOrPresent(message = "Hire date cannot be in the future") //
        LocalDate hireDate,

        @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than 0") //
        @Digits(integer = 8, fraction = 2, message = "Salary format invalid (max 8 digits, 2 decimal places)") //
        BigDecimal salary,

        @NotEmpty(message = "Email is mandatory for this API version") //
        @Email(message = "Email should be valid") //
        @Size(max = 30, message = "Email cannot exceed 30 characters") //
        String email) {
}
