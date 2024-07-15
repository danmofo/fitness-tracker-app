package com.dmoffat.fitnesstracker.service;

import com.dmoffat.fitnesstracker.dao.BodyWeightDao;
import com.dmoffat.fitnesstracker.model.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBodyWeightService {
    private final BodyWeightDao bodyWeightDao;

    @Autowired
    public UserBodyWeightService(BodyWeightDao bodyWeightDao) {
        this.bodyWeightDao = bodyWeightDao;
    }

    public void logBodyWeight(@NotNull User user, Double bodyWeight) {
        bodyWeightDao.create(user.getId(), bodyWeight);
    }
}
