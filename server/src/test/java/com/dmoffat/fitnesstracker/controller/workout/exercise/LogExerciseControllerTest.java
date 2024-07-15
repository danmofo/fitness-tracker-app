package com.dmoffat.fitnesstracker.controller.workout.exercise;

import com.dmoffat.fitnesstracker.RequestHelpers;
import com.dmoffat.fitnesstracker.model.WorkoutExercise;
import com.dmoffat.fitnesstracker.model.request.workout.exercise.LogExerciseRequest;
import com.dmoffat.fitnesstracker.model.response.ErrorCode;
import com.dmoffat.fitnesstracker.service.JsonMappingService;
import com.dmoffat.fitnesstracker.service.LogExerciseService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LogExerciseControllerTest {
    @Autowired @MockBean private LogExerciseService logExerciseService;
    @Autowired private MockMvc mockMvc;
    @Autowired private JsonMappingService jsonMappingService;

    @BeforeEach
    void setUp() {
        var workoutExercise = new WorkoutExercise();
        workoutExercise.setId(999);
        when(logExerciseService.logExercise(any(), any())).thenReturn(workoutExercise);
    }

    @Test
    @Transactional
    void shouldReturnErrorResponseWhenExerciseNotLogged() throws Exception {
        when(logExerciseService.logExercise(any(), any())).thenReturn(null);

        var request = new LogExerciseRequest(
            1,
            1,
            100.0,
            1,
            1,
            null,
            null
        );

        mockMvc.perform(logExerciseRequest(request))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success", Matchers.is(false)));
    }

    @Test
    @Transactional
    void shouldReturnSuccessResponseWhenExerciseLogged() throws Exception {
        var request = new LogExerciseRequest(
            1,
            1,
            100.10,
            1,
            1,
            null,
            null
        );

        mockMvc.perform(logExerciseRequest(request))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success", Matchers.is(true)))
            .andExpect(jsonPath("$.workoutExerciseId").isNotEmpty());
    }

    // This tests @NotNull annotation
    @Test
    @Transactional
    void shouldReturnErrorResponseWhenFieldsAreMissingFromRequest() throws Exception {
        var request = new LogExerciseRequest(
            null,
            null,
            null,
            null,
            null,
            null,
            null
        );

        mockMvc.perform(logExerciseRequest(request))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errorCode").value(ErrorCode.VALIDATION.toString()))
            .andExpect(jsonPath("$.validationErrors.length()").value(5))
            .andExpect(jsonPath("$.validationErrors[*].field",
                containsInAnyOrder("workoutId", "exerciseId", "weight", "sets", "reps")));
    }

    // This tests fields with specific rules
    @Test
    @Transactional
    void shouldReturnErrorResponseWhenWeightSetsAndRepsDontHaveMinValue() throws Exception {
        var request = new LogExerciseRequest(
            1,
            1,
            0.00,
            0,
            -1,
            null,
            null
        );

        mockMvc.perform(logExerciseRequest(request))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errorCode").value(ErrorCode.VALIDATION.toString()))
            .andExpect(jsonPath("$.validationErrors.length()").value(3))
            .andExpect(jsonPath("$.validationErrors[*].field",
                containsInAnyOrder("weight", "sets", "reps")))
            .andDo(print());
    }

    private MockHttpServletRequestBuilder logExerciseRequest(Object request) {
        var sessionId = RequestHelpers.authenticate(mockMvc, "danmofo@gmail.com", "password");
        return post("/api/v1/workout/1/exercise/")
            .header("X-Auth-Token", sessionId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonMappingService.writeToJson(request));
    }
}