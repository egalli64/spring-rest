/*
 * A Spring Boot RESTful application 
 * 
 * https://github.com/egalli64/swr
 */
package com.example.swr;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class HelloCtrlTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void getHello() throws Exception {
        mvc.perform(get("/api/m1/s2/hello")) //
                .andExpect(status().isOk()) //
                .andExpect(jsonPath("$.message").value("Hello World!"));
    }

    @Test
    void getAnswer() throws Exception {
        mvc.perform(get("/api/m1/s2/answer")) //
                .andExpect(status().isOk()) //
                // since a simple value is expected, no need to go through JSON
                .andExpect(content().string("42"));
    }

    @Test
    void putHello() throws Exception {
        mvc.perform(put("/api/m1/s2/hello")) //
                .andExpect(status().isMethodNotAllowed());
    }
}
