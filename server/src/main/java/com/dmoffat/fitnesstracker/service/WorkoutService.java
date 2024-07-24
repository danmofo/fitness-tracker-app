package com.dmoffat.fitnesstracker.service;

import com.dmoffat.fitnesstracker.dao.WorkoutDao;
import com.dmoffat.fitnesstracker.dao.WorkoutExerciseDao;
import com.dmoffat.fitnesstracker.model.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WorkoutService {
    @Autowired private WorkoutDao workoutDao;
    @Autowired private WorkoutExerciseDao workoutExerciseDao;

    public Workout createWorkout(User owner) {
        return workoutDao.create(owner.getId());
    }

    public boolean finishWorkout(@NotNull User user, @NotNull Integer workoutId, String notes) {
        var workout = workoutDao.findOneWithUser(workoutId);
        if (workout == null) {
            return false;
        }

        if (!workout.getUser().equals(user)) {
            return false;
        }

        workoutDao.updateFinishedOnAndNotes(workoutId, LocalDateTime.now(), notes);

        return true;
    }

    public List<ExerciseWithCompletedSets> listExercisesForWorkoutId(@NotNull User user, @NotNull Integer workoutId) {
        var workout = workoutDao.findOneWithUser(workoutId);
        if (workout == null) {
            return Collections.emptyList();
        }

        if (!workout.getUser().equals(user)) {
            return Collections.emptyList();
        }

        // Fetch ALL the exercises for this workout
        var allWorkoutExercises = workoutExerciseDao.findAllByWorkoutId(workoutId);

        // Create a map of Exercise => WorkoutExercise so it's easier to work with
        Map<Exercise, List<WorkoutExercise>> exerciseToWorkoutExerciseMap = allWorkoutExercises.stream()
            .collect(Collectors.groupingBy(WorkoutExercise::getExercise));

        // Map it to our model
        return exerciseToWorkoutExerciseMap.entrySet().stream().map(entry -> {
            var exercise = entry.getKey();
            var workoutExercises = entry.getValue();

            var exerciseWithCompletedSets = new ExerciseWithCompletedSets();
            exerciseWithCompletedSets.setId(exercise.getId());
            exerciseWithCompletedSets.setName(exercise.getName());

            var completedSets = workoutExercises.stream()
                .map(this::mapFromWorkoutExercise)
                .toList();
            exerciseWithCompletedSets.setCompleted(completedSets);

            return exerciseWithCompletedSets;
        }).toList();
    }

    private CompletedSet mapFromWorkoutExercise(WorkoutExercise workoutExercise) {
        var completedSet = new CompletedSet();
        completedSet.setWeight(workoutExercise.getWeight());
        completedSet.setSets(workoutExercise.getSets());
        completedSet.setReps(workoutExercise.getReps());
        completedSet.setNotes(workoutExercise.getNotes());
        completedSet.setEquipment(workoutExercise.getEquipment());
        return completedSet;
    }

}
