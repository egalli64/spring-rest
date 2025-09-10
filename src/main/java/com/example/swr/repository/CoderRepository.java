/*
 * Spring Boot Web REST tutorial 
 * 
 * https://github.com/egalli64/spring-rest
 */
package com.example.swr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.swr.model.Coder;

@Repository
public interface CoderRepository extends JpaRepository<Coder, Integer> {
    boolean existsByFirstNameAndLastName(String firstName, String lastName);
}
