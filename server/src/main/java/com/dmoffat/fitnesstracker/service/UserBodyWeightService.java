package com.dmoffat.fitnesstracker.service;

import com.dmoffat.fitnesstracker.dao.BodyWeightDao;
import com.dmoffat.fitnesstracker.model.User;
import com.dmoffat.fitnesstracker.model.UserBodyWeight;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserBodyWeightService {
    private final BodyWeightDao bodyWeightDao;

    @Autowired
    public UserBodyWeightService(BodyWeightDao bodyWeightDao) {
        this.bodyWeightDao = bodyWeightDao;
    }

    /**
     * Logs a users body weight - if they have already logged their weight today, this method will overwrite that
     * value with the most recent one.
     */
    public void logBodyWeight(@NotNull User user, Double bodyWeight) {
        var todaysBodyWeightId = bodyWeightDao.findIdByUserIdAndLoggedOn(user.getId(), LocalDate.now());
        if(todaysBodyWeightId == null) {
            bodyWeightDao.create(user.getId(), bodyWeight);
            return;
        }

        bodyWeightDao.updateWeight(todaysBodyWeightId, bodyWeight);
    }

    public List<UserBodyWeight> findAll(@NotNull User user) {
        return bodyWeightDao.findAll(user.getId());
    }
}
