package com.dmoffat.fitnesstracker.service;

import com.dmoffat.fitnesstracker.dao.ExerciseDao;
import com.dmoffat.fitnesstracker.dao.WorkoutDao;
import com.dmoffat.fitnesstracker.dao.WorkoutExerciseDao;
import com.dmoffat.fitnesstracker.model.User;
import com.dmoffat.fitnesstracker.model.WorkoutExercise;
import com.dmoffat.fitnesstracker.model.request.workout.exercise.LogExerciseRequest;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogExerciseService {
    private static final Logger logger = LoggerFactory.getLogger(LogExerciseService.class);

    private final ExerciseDao exerciseDao;
    private final WorkoutDao workoutDao;
    private final WorkoutExerciseDao workoutExerciseDao;

    @Autowired
    public LogExerciseService(
            ExerciseDao exerciseDao,
            WorkoutDao workoutDao,
            WorkoutExerciseDao workoutExerciseDao) {
        this.exerciseDao = exerciseDao;
        this.workoutDao = workoutDao;
        this.workoutExerciseDao = workoutExerciseDao;
    }

    public WorkoutExercise logExercise(@NotNull User workoutOwner, @NotNull LogExerciseRequest request) {
        logger.debug("Logging exercise for: " + workoutOwner.getEmail());

        logger.debug("Fetching workout (" + request.workoutId() + ") w/ User");
        var workout = workoutDao.findOneWithUser(request.workoutId());

        // Does the workout exist?
        if (workout == null) {
            logger.debug("Workout does not exist.");
            return null;
        }

        var hasPermission = workout.getUser().getId().equals(workoutOwner.getId());
        if (!hasPermission) {
            logger.debug("User does not own the workout");
            return null;
        }

        // Does the exercise exist?
        logger.debug("Fetching exercise (" + request.exerciseId() + ")");
        var exercise = exerciseDao.findOne(request.exerciseId());
        if (exercise == null) {
            logger.debug("Exercise does not exist");
            return null;
        }

        // Assemble the workout exercise to create
        var workoutExercise = new WorkoutExercise();
        workoutExercise.setWorkout(workout);
        workoutExercise.setExercise(exercise);
        workoutExercise.setWeight(request.weight());
        workoutExercise.setReps(request.reps());
        workoutExercise.setSets(request.sets());
        workoutExercise.setNotes(request.notes());
        workoutExercise.setEquipment(request.equipment());
        workoutExercise.setCreatedOn(LocalDateTime.now());

        // Find any matching records in the same workout, with the same weight, reps and equipment
        var duplicate = workoutExerciseDao.findByWorkoutExerciseByWeightRepsAndEquipment(workoutExercise);
        if (duplicate == null) {
            logger.debug("This is a brand new workout exercise.");
            Integer workoutExerciseId = workoutExerciseDao.create(workoutExercise);
            workoutExercise.setId(workoutExerciseId);
            return workoutExercise;
        }

        logger.debug("This exercise has already been performed before, incrementing sets.");
        workoutExercise.setId(duplicate.getId());
        workoutExercise.setSets(duplicate.getSets() + 1);
        workoutExerciseDao.updateSets(duplicate.getId(), duplicate.getSets());

        return workoutExercise;
    }
}
