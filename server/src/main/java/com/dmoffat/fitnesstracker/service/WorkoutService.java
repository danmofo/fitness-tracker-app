package com.dmoffat.fitnesstracker.service;

import com.dmoffat.fitnesstracker.dao.WorkoutDao;
import com.dmoffat.fitnesstracker.dao.WorkoutExerciseDao;
import com.dmoffat.fitnesstracker.model.ExerciseWithCompletedSets;
import com.dmoffat.fitnesstracker.model.User;
import com.dmoffat.fitnesstracker.model.Workout;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
            return null;
        }

        if (!workout.getUser().equals(user)) {
            return null;
        }

        // Fetch ALL the exercises for this workout
        var workoutExercises = workoutExerciseDao.findAllByWorkoutId(workoutId);

        // Map them to our model
        var exerciseWithCompletedSets = new ExerciseWithCompletedSets();
        for (var workoutExercise : workoutExercises) {
            // todo
        }


        return exerciseWithCompletedSets;
    }
}
