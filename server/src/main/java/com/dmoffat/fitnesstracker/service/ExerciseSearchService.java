package com.dmoffat.fitnesstracker.service;

import com.dmoffat.fitnesstracker.dao.ExerciseDao;
import com.dmoffat.fitnesstracker.model.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseSearchService {
    private final ExerciseDao exerciseDao;

    @Autowired
    public ExerciseSearchService(ExerciseDao exerciseDao) {
        this.exerciseDao = exerciseDao;
    }

    public List<Exercise> search() {
        return exerciseDao.findAll();
    }
}
