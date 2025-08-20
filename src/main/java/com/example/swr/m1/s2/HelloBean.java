/*
 * A Spring Boot RESTful application 
 * 
 * https://github.com/egalli64/swr
 */
package com.example.swr.m1.s2;

/**
 * A very simple JavaBean
 */
public class HelloBean {
    private String message;

    public HelloBean() {
    }

    public HelloBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelloBean [message=" + message + "]";
    }
}
