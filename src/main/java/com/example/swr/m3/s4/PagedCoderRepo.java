/*
 * A Spring Boot RESTful application 
 * 
 * https://github.com/egalli64/swr
 */
package com.example.swr.m3.s4;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.swr.dao.Coder;

/**
 * For paging and sorting
 */
@Repository
public interface PagedCoderRepo extends JpaRepository<Coder, Integer> {
}
