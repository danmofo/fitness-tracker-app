package com.dmoffat.fitnesstracker.service;

import com.dmoffat.fitnesstracker.dao.WorkoutDao;
import com.dmoffat.fitnesstracker.model.User;
import com.dmoffat.fitnesstracker.model.Workout;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WorkoutService {
    @Autowired private WorkoutDao workoutDao;

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
}
