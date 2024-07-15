package com.dmoffat.fitnesstracker.controller.user;

import com.dmoffat.fitnesstracker.RequestHelpers;
import com.dmoffat.fitnesstracker.model.request.user.LogBodyWeightRequest;
import com.dmoffat.fitnesstracker.model.response.ErrorCode;
import com.dmoffat.fitnesstracker.service.JsonMappingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LogBodyWeightControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private JsonMappingService jsonMappingService;

    @Test
    @Transactional
    void shouldReturnSuccessResponseWhenBodyWeightLogged() throws Exception {
        var request = new LogBodyWeightRequest(100.5);

        mockMvc.perform(logBodyWeightRequest(request))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andDo(print());
    }

    @Test
    @Transactional
    void shouldReturnErrorResponseWhenWeightIsMissingFromRequest() throws Exception {
        var request = new LogBodyWeightRequest(null);

        mockMvc.perform(logBodyWeightRequest(request))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errorCode").value(ErrorCode.VALIDATION.toString()))
            .andExpect(jsonPath("$.validationErrors.length()").value(1))
            .andExpect(jsonPath("$.validationErrors[*].field", containsInAnyOrder("weight")));
    }

    private MockHttpServletRequestBuilder logBodyWeightRequest(Object request) {
        var sessionId = RequestHelpers.authenticate(mockMvc, "danmofo@gmail.com", "password");
        return post("/api/v1/user/weight/")
            .header("X-Auth-Token", sessionId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonMappingService.writeToJson(request));
    }
}