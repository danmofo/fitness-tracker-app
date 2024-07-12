package com.dmoffat.fitnesstracker;

import com.dmoffat.fitnesstracker.controller.AuthController;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class RequestHelpers {
    private static final ObjectMapper jsonSerialiser;

    static {
        jsonSerialiser = new ObjectMapper();
        jsonSerialiser.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static MockHttpServletRequestBuilder loginRequest(String email, String password) {
        var loginRequest = new AuthController.LoginRequest(email, password);
        var body = Objects.requireNonNull(toJson(loginRequest));
        return post("/api/v1/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body);
    }

    private static String toJson(Object object) {
        try {
            return jsonSerialiser.writeValueAsString(object);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Authenticate with the given details.
     * @return The session token if authenticated successfully.
     */
    public static String authenticate(MockMvc mockMvc, String email, String password) {
        try {
            var request = loginRequest(email, password);
            var result = mockMvc.perform(request).andReturn();
            return result.getResponse().getHeader("X-Auth-Token");
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
