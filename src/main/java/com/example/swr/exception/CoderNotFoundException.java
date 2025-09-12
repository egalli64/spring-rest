/*
 * Spring Boot Web REST tutorial 
 * 
 * https://github.com/egalli64/spring-rest
 */
package com.example.swr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CoderNotFoundException extends ResponseStatusException {
    private static final long serialVersionUID = 2610181011752998047L;

    public CoderNotFoundException(Integer id) {
        this(id, null);
    }

    public CoderNotFoundException(Integer id, Throwable cause) {
        super(HttpStatus.NOT_FOUND, String.format("Coder %d not found", id), cause);
    }
}
