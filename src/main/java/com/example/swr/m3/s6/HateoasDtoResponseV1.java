/*
 * Spring Boot Web REST tutorial 
 * 
 * https://github.com/egalli64/spring-rest
 */
package com.example.swr.m3.s6;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import com.example.swr.m3.s5.EntityV2;

/**
 * HATEOAS Coder DTO for response - version 1 (no email)
 */
public class HateoasDtoResponseV1 extends RepresentationModel<HateoasDtoResponseV1> {
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate hireDate;
    private BigDecimal salary;

    public HateoasDtoResponseV1() {
    }

    public HateoasDtoResponseV1(Integer coderId, String firstName, String lastName, LocalDate hireDate, BigDecimal salary) {
        this.id = coderId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireDate = hireDate;
        this.salary = salary;
    }

    /**
     * Entity to Response DTO mapper
     */
    public HateoasDtoResponseV1(EntityV2 coder) {
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
