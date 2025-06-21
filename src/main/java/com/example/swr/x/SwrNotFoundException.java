package com.example.swr.zed;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@SuppressWarnings("serial")
public class SwrNotFoundException extends ResponseStatusException {
    public SwrNotFoundException(Integer id) {
        this(id, null);
    }

    public SwrNotFoundException(Integer id, Throwable cause) {
        super(HttpStatus.NOT_FOUND, String.format("Coder %d not found", id), cause);
    }
}
