package com.dmoffat.fitnesstracker.service;

import com.dmoffat.fitnesstracker.dao.UserDao;
import com.dmoffat.fitnesstracker.dao.WorkoutDao;
import com.dmoffat.fitnesstracker.model.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class WorkoutService {
    @Autowired private WorkoutDao workoutDao;
    @Autowired private UserDao userDao;

    public Workout createWorkout(User user) {
        var ownerUser = userDao.findByEmail(user.getUsername());
        return workoutDao.create(ownerUser.getId());
    }
}
