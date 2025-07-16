/*
 * A Spring Boot RESTful application 
 * 
 * https://github.com/egalli64/swr
 */
package com.example.swr.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoderRepo extends CrudRepository<Coder, Integer> {
}
