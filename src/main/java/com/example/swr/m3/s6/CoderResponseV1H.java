/*
 * A Spring Boot RESTful application 
 * 
 * https://github.com/egalli64/swr
 */
package com.example.swr.m3.s6;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import com.example.swr.m3.s5.VersionedCoder;

/**
 * HATEOAS Coder DTO for response - version 1 (no email)
 */
public class CoderResponseV1H extends RepresentationModel<CoderResponseV1H> {
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate hireDate;
    private BigDecimal salary;

    public CoderResponseV1H() {
    }

    public CoderResponseV1H(Integer coderId, String firstName, String lastName, LocalDate hireDate, BigDecimal salary) {
        this.id = coderId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireDate = hireDate;
        this.salary = salary;
    }

    /**
     * Entity to Response DTO mapper
     */
    public CoderResponseV1H(VersionedCoder coder) {
        this(coder.getId(), coder.getFirstName(), coder.getLastName(), coder.getHireDate(), coder.getSalary());
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "CoderResponseV1H [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", hireDate="
                + hireDate + ", salary=" + salary + "]";
    }
}
