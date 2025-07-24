/*
 * A Spring Boot RESTful application 
 * 
 * https://github.com/egalli64/swr
 */
package com.example.swr.m3.s3;

/**
 * Used for validation purposes
 */
public class CoderNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 6310896164313113339L;

    public CoderNotFoundException(String message) {
        super(message);
    }
}