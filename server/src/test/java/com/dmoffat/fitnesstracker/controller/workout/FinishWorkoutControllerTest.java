package com.dmoffat.fitnesstracker.controller.workout;

import com.dmoffat.fitnesstracker.RequestHelpers;
import com.dmoffat.fitnesstracker.model.User;
import com.dmoffat.fitnesstracker.model.request.workout.FinishWorkoutRequest;
import com.dmoffat.fitnesstracker.service.JsonMappingService;
import com.dmoffat.fitnesstracker.service.WorkoutService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FinishWorkoutControllerTest {
    @Autowired @MockBean private WorkoutService workoutService;
    @Autowired private MockMvc mockMvc;
    @Autowired private JsonMappingService jsonMappingService;

    @Test
    @Transactional
    void shouldReturnErrorResponseWhenExerciseNotLogged() throws Exception {
        when(workoutService.finishWorkout(new User(1), 1, "Some notes")).thenReturn(false);

        var request = new FinishWorkoutRequest("Some notes");

        mockMvc.perform(finishExerciseRequest(1, request))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success", Matchers.is(false)));
    }

    @Test
    @Transactional
    void shouldReturnSuccessResponseWhenExerciseLogged() throws Exception {
        when(workoutService.finishWorkout(new User(1), 1, "Some notes")).thenReturn(true);

        var request = new FinishWorkoutRequest("Some notes");

        mockMvc.perform(finishExerciseRequest(1, request))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success", Matchers.is(true)));
    }


    private MockHttpServletRequestBuilder finishExerciseRequest(Integer workoutId, Object request) {
        var sessionId = RequestHelpers.authenticate(mockMvc, "danmofo@gmail.com", "password");
        return post("/api/v1/workout/{workoutId}/finish", workoutId)
            .header("X-Auth-Token", sessionId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonMappingService.writeToJson(request));
    }
}
