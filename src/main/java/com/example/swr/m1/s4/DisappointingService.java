/*
 * Spring Boot Web REST tutorial 
 * 
 * https://github.com/egalli64/spring-rest
 */
package com.example.swr.m1.s4;

import org.springframework.stereotype.Service;

@Service
public class DisappointingService {
    public void alwaysFailing() {
        throw new IllegalStateException("Whatever");
    }
}
