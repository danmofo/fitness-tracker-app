package com.dmoffat.fitnesstracker.service;

import com.dmoffat.fitnesstracker.dao.ExerciseDao;
import com.dmoffat.fitnesstracker.dao.WorkoutDao;
import com.dmoffat.fitnesstracker.dao.WorkoutExerciseDao;
import com.dmoffat.fitnesstracker.model.Exercise;
import com.dmoffat.fitnesstracker.model.User;
import com.dmoffat.fitnesstracker.model.Workout;
import com.dmoffat.fitnesstracker.model.WorkoutExercise;
import com.dmoffat.fitnesstracker.model.request.workout.exercise.LogExerciseRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LogExerciseServiceTest {
    @InjectMocks private LogExerciseService logExerciseService;
    @Mock private WorkoutDao workoutDao;
    @Mock private WorkoutExerciseDao workoutExerciseDao;
    @Mock private ExerciseDao exerciseDao;

    @Test
    void shouldNotLogExerciseForNonExistentWorkout() {
        var request = new LogExerciseRequest(
            1, // This doesn't exist
            1,
            100,
            1,
            3,
            "Do something",
            null
        );

        WorkoutExercise result = logExerciseService.logExercise(new User(), request);
        assertNull(result);
    }

    @Test
    void shouldNotLogExerciseForIfUserDoesntOwnWorkout() {
        // Create a workout belonging to someone else
        var workout = new Workout();
        workout.setUser(new User(2));
        when(workoutDao.findOneWithUser(1)).thenReturn(workout);

        var user = new User(1);
        var request = new LogExerciseRequest(
            1, // User doesn't own this
            1,
            100,
            1,
            3,
            "Do something",
            null
        );

        WorkoutExercise result = logExerciseService.logExercise(user, request);
        assertNull(result);
    }

    @Test
    void shouldLogNewExerciseIfNotADuplicateWithSameIdWeightRepsAndEquipment() {
        // Create a workout belonging to user
        var user = new User(1);
        var workout = new Workout(1);
        workout.setUser(user);
        when(workoutDao.findOneWithUser(1)).thenReturn(workout);

        // Create an exercise
        var exercise = new Exercise(1);
        when(exerciseDao.findOne(1)).thenReturn(exercise);

        // Mock #create to return a fixed value
        when(workoutExerciseDao.create(any(WorkoutExercise.class))).thenReturn(1);

        var request = new LogExerciseRequest(
            1,
            1,
            100,
            1,
            3,
            "My notes",
            List.of("BELT")
        );

        WorkoutExercise result = logExerciseService.logExercise(user, request);
        assertEquals(1, result.getId());
        assertEquals(1, result.getWorkout().getId());
        assertEquals(1, result.getExercise().getId());
        assertEquals(100, result.getWeight());
        assertEquals(1, result.getSets());
        assertEquals(3, result.getReps());
        assertEquals("My notes", result.getNotes());
        assertEquals(List.of("BELT"), result.getEquipment());
        assertNotNull(result.getCreatedOn());
    }

    @Test
    void shouldUpdateExistingExerciseIfDuplicateWithSameIdWeightRepsAndEquipment() {
        // Create a workout belonging to user
        var user = new User(2);
        var workout = new Workout(1);
        workout.setUser(user);
        when(workoutDao.findOneWithUser(1)).thenReturn(workout);

        // Create an exercise
        var exercise = new Exercise(1);
        when(exerciseDao.findOne(1)).thenReturn(exercise);

        // Create a duplicate - this should have its sets incremented
        var initialSets = 1;
        var duplicate = new WorkoutExercise(1);
        duplicate.setSets(initialSets);
        when(workoutExerciseDao.findByWorkoutExerciseByWeightRepsAndEquipment(any(WorkoutExercise.class)))
            .thenReturn(duplicate);

        var request = new LogExerciseRequest(
            1,
            1,
            100,
            1,
            3,
            "My notes",
            List.of("BELT")
        );

        WorkoutExercise result = logExerciseService.logExercise(user, request);
        assertEquals(initialSets + 1, result.getSets());
        assertEquals(duplicate.getId(), result.getId());
    }
}