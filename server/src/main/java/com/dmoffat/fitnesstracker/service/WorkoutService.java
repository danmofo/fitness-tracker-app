package com.dmoffat.fitnesstracker.service;

import com.dmoffat.fitnesstracker.dao.UserDao;
import com.dmoffat.fitnesstracker.dao.WorkoutDao;
import com.dmoffat.fitnesstracker.model.User;
import com.dmoffat.fitnesstracker.model.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkoutService {
    @Autowired private WorkoutDao workoutDao;
    @Autowired private UserDao userDao;

    public Workout createWorkout(User owner) {
        return workoutDao.create(owner.getId());
    }
}
