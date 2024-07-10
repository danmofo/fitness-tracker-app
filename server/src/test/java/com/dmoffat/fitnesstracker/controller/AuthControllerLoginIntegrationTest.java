package com.dmoffat.fitnesstracker.controller;

import com.dmoffat.fitnesstracker.model.response.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static com.dmoffat.fitnesstracker.db.tables.SpringSession.SPRING_SESSION;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// todo: Look at testcontainers for starting a brand new database before each integration test so that we don't
//       need to worry about existing DB state.
@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerLoginIntegrationTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private DSLContext db;

    @Test
    void shouldReturnErrorWhenCredentialsMissing() throws Exception {
        this.mockMvc.perform(loginRequest(null, null))
            .andExpect(status().is4xxClientError())
            .andExpect(jsonPath("$.errorCode").value(ErrorCode.VALIDATION.toString()))
            .andExpect(jsonPath("$.validationErrors.length()").value(2))
            .andExpect(jsonPath("$.validationErrors[*].field", containsInAnyOrder("email", "password")))
            .andDo(print());
    }

    @Test
    void shouldReturnErrorWhenEmailDoesNotMatchAnyUserRecords() throws Exception {
        this.mockMvc.perform(loginRequest("i-do-not-exist@gmail.com", "password"))
            .andExpect(status().is4xxClientError())
            .andExpect(jsonPath("$.errorCode").value(ErrorCode.INVALID_CREDENTIALS.toString()))
            .andDo(print());
    }

    @Test
    void shouldReturnErrorWhenPasswordDoesNotMatchUsersPassword() throws Exception {
        this.mockMvc.perform(loginRequest("danmofo@gmail.com", "wrong-password"))
            .andExpect(status().is4xxClientError())
            .andExpect(jsonPath("$.errorCode").value(ErrorCode.INVALID_CREDENTIALS.toString()))
            .andDo(print());
    }

    /**
     * This tests a few things after authenticating:
     * - Session token is set in X-Auth-Token header
     * - Session is saved to the database
     * - Session in database contains the user that's authenticated
     */
    @Test
    void shouldReturnSessionTokenInHeaderAndCreateSessionAfterAuthentication() throws Exception {
        MvcResult result = this.mockMvc.perform(loginRequest("danmofo@gmail.com", "password"))
                .andExpect(status().isOk())
                .andExpect(header().exists("X-Auth-Token"))
                .andExpect(jsonPath("$.success").value(true))
                .andReturn();

        // Grab the session ID from the response header.
        String sessionId = result.getResponse().getHeader("X-Auth-Token");

        // Check it got saved in the DB
        String emailSavedInSession = findPrincipalForSessionId(sessionId);
        assertEquals("danmofo@gmail.com", emailSavedInSession);
    }

    private String findPrincipalForSessionId(String sessionId) {
        String emailSavedInSession = db.selectFrom(SPRING_SESSION)
            .where(SPRING_SESSION.SESSION_ID.eq(sessionId))
            .fetchOne(SPRING_SESSION.PRINCIPAL_NAME);

        // Remove the record
        // todo: Remove this when we've implemented the todo at the top of the class.
        db.delete(SPRING_SESSION)
            .where(SPRING_SESSION.SESSION_ID.eq(sessionId))
            .execute();

        return emailSavedInSession;
    }

    private MockHttpServletRequestBuilder loginRequest(String email, String password) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        AuthController.LoginRequest request = new AuthController.LoginRequest(email, password);

        return post("/api/v1/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request));
    }
}