/*
 * Spring Boot Web REST tutorial 
 * 
 * https://github.com/egalli64/spring-rest
 */
package com.example.swr.m3.s4;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.swr.model.Coder;

/**
 * For paging and sorting
 */
@Repository
public interface PagedCoderRepo extends JpaRepository<Coder, Integer> {
}
