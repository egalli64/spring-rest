/*
 * Spring Boot Web REST tutorial 
 * 
 * https://github.com/egalli64/spring-rest
 */
package com.example.swr.m3.s5;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DTOs are not considered here, we care only about the entity
 */
@Repository
public interface VersionedRepository extends JpaRepository<EntityV2, Integer> {
}
