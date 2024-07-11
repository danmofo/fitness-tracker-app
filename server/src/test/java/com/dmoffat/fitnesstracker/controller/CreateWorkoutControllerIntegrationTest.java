package com.dmoffat.fitnesstracker.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CreateWorkoutControllerIntegrationTest {
    private static final Logger logger = LoggerFactory.getLogger(CreateWorkoutControllerIntegrationTest.class);

    @Autowired private DSLContext db;
    @Autowired private MockMvc mockMvc;

    @Test
    @Transactional
    void shouldReturnCorrectResponseWhenWorkoutCreated() throws Exception {
        mockMvc.perform(authenticatedRequest("/api/v1/workout/"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.workoutId").isNotEmpty());
    }

    private String createSessionId() throws Exception {
        logger.info("Creating session ID");
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        AuthController.LoginRequest loginRequest = new AuthController.LoginRequest("danmofo@gmail.com", "password");

        MockHttpServletRequestBuilder request = post("/api/v1/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(loginRequest));

        return mockMvc.perform(request).andReturn().getResponse().getHeader("X-Auth-Token");
    }

    private MockHttpServletRequestBuilder authenticatedRequest(String endpoint) throws Exception {
        String sessionId = createSessionId();
        logger.debug("Performing authenticated request to: " + endpoint + " with session ID: " + sessionId);
        return post(endpoint)
            .header("X-Auth-Token", sessionId);
    }
}