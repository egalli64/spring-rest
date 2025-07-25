/*
 * A Spring Boot RESTful application 
 * 
 * https://github.com/egalli64/swr
 */
package com.example.swr.m3.s5;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DTOs are not considered here, we care only about the entity
 */
@Repository
public interface VersionedCoderRepo extends JpaRepository<VersionedCoder, Integer> {
}
