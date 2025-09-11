/*
 * Spring Boot Web REST tutorial 
 * 
 * https://github.com/egalli64/spring-rest
 */
package com.example.swr;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.swr.m2.s2.GetController;
import com.example.swr.model.Coder;
import com.example.swr.repository.CoderRepository;

@WebMvcTest(GetController.class)
class GetControllerTest {
    @MockitoBean
    private CoderRepository repo;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void codersEmpty() throws Exception {
        List<Coder> coders = List.of();

        // mock the repo
        when(repo.findAll()).thenReturn(coders);

        mockMvc.perform(get("/api/m2/s2/coders")) //
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));

        verify(repo, times(1)).findAll();
    }

    @Test
    void codersOne() throws Exception {

        List<Coder> coders = List
                .of(new Coder(103, "Alexander", "Hunold", LocalDate.of(2025, 6, 30), BigDecimal.valueOf(8500)));

        // mock the repo
        when(repo.findAll()).thenReturn(coders);

        mockMvc.perform(get("/api/m2/s2/coders")) //
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1))) //
                .andExpect(jsonPath("$[0].id", is(103))) //
                .andExpect(jsonPath("$[0].firstName", is("Alexander"))) //
                .andExpect(jsonPath("$[0].lastName", is("Hunold"))) //
                .andExpect(jsonPath("$[0].hireDate", is("2025-06-30"))) //
                .andExpect(jsonPath("$[0].salary", is(8500)));

        verify(repo, times(1)).findAll();
    }
}
