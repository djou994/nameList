package com.nameList.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nameList.model.Name;
import com.nameList.service.NamesService;
import jakarta.servlet.ServletException;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class NamesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NamesService service;

    @Test
    @Order(1)
    void create() throws Exception {
        URI uri = new URI("/names/new");
        Name name = new Name("Pedro");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(name);

        //faz a requisição e já garante 200 como resposta.
        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(Response.SC_OK));
        assertTrue(service.verifyIfExists("Pedro"));
    }

    @Test
    @Order(2)
    void verifyIfExists() {
        assertTrue(service.verifyIfExists("Pedro"));
        assertTrue(service.verifyIfExists("pedro"));
        assertFalse(service.verifyIfExists("João"));
        assertFalse(service.verifyIfExists("joão"));
    }

    @Test
    @Order(3)
    void createList() throws Exception {
        URI uri = new URI("/names");
        List<Name> names = new ArrayList<>();
        names.add(new Name("Marcos"));
        names.add(new Name("John"));
        names.add(new Name("Andrew"));

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(names);

        //faz a requisição e já garante 200 como resposta.
        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(Response.SC_OK));
        assertEquals(7, service.getAllNames().size());
    }

    @Test
    @Order(4)
    void createFromString() throws Exception {
        URI uri = new URI("/names/list");

        String names = "Mateus, Antonio, Anderson";

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(names);


        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(Response.SC_OK));
        assertEquals(4, service.getAllNames().size());
    }

    @Test
    @Order(5)
    void getAllNames() throws Exception {
        URI uri = new URI("/names");

        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers.status().is(Response.SC_OK));
    }



    @Test
    @Order(6)
    void createListDuplicated() throws Exception {
        URI uri = new URI("/names");
        List<Name> names = new ArrayList<>();
        names.add(new Name("Pedro"));
        names.add(new Name("João"));
        names.add(new Name("Marcos"));
        names.add(new Name("John"));
        names.add(new Name("Marcos"));

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(names);


        Exception exception = assertThrows(ServletException.class, () ->
            mockMvc.perform(MockMvcRequestBuilders
                    .post(uri)
                    .content(json)
                    .contentType(MediaType.APPLICATION_JSON))
        );

        String expectedMessage = "There is duplicated names at the list";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @Order(7)
    void createFromStringDuplicated() throws Exception {
        URI uri = new URI("/names/list");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString("João, Marcos, Antonio, Andrew, João");

        Exception exception = assertThrows(ServletException.class, () ->
            mockMvc.perform(MockMvcRequestBuilders
                    .post(uri)
                    .content(json)
                    .contentType(MediaType.APPLICATION_JSON))
        );

        String expectedMessage = "There is duplicated names at the list";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}