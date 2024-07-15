package com.dmoffat.fitnesstracker.service;

import com.dmoffat.fitnesstracker.dao.WorkoutDao;
import com.dmoffat.fitnesstracker.model.User;
import com.dmoffat.fitnesstracker.model.Workout;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WorkoutServiceTest {
    @InjectMocks private WorkoutService workoutService;
    @Mock private WorkoutDao workoutDao;

    @Test
    void shouldFinishWorkout() {
        // Prepare
        var workout = new Workout();
        workout.setId(1);
        workout.setUser(new User(1));
        when(workoutDao.findOneWithUser(1)).thenReturn(workout);

        // Execute
        boolean success = workoutService.finishWorkout(new User(1), 1, "Some notes");

        // Test
        assertTrue(success);
    }

    @Test
    void shouldNotFinishWorkoutWhenWorkoutDoesNotExist() {
        boolean success = workoutService.finishWorkout(new User(1), 999, "Some notes");
        assertFalse(success);
    }

    @Test
    void shouldNotFinishWorkoutWhenUserDoesNotOwnWorkout() {
        // Prepare
        var workout = new Workout();
        workout.setId(1);
        workout.setUser(new User(222));
        when(workoutDao.findOneWithUser(2)).thenReturn(workout);

        // Execute
        boolean success = workoutService.finishWorkout(new User(1), 2, "Some notes");

        // Test
        assertFalse(success);
    }
}