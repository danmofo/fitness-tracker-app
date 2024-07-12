package com.dmoffat.fitnesstracker.controller;

import com.dmoffat.fitnesstracker.RequestHelpers;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
        mockMvc.perform(authorisedRequest("/api/v1/workout/"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.workoutId").isNotEmpty());
    }

    private MockHttpServletRequestBuilder authorisedRequest(String endpoint) {
        var sessionId = RequestHelpers.authenticate(mockMvc, "danmofo@gmail.com", "password");

        logger.debug("Performing authenticated request to: " + endpoint + " with session ID: " + sessionId);
        return post(endpoint)
            .header("X-Auth-Token", sessionId);
    }
}