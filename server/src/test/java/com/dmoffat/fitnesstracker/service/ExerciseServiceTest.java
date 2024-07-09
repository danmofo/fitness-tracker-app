package com.dmoffat.fitnesstracker.service;

import com.dmoffat.fitnesstracker.dao.ExerciseDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExerciseServiceTest {
    @Autowired private ExerciseService exerciseService;
    @Autowired private ExerciseDao exerciseDao;

    @Test
    void testJooqIntegration() {
        exerciseService.printExercises();
    }

    @Test
    void testDao() {
        exerciseDao.findAll();
        System.out.println(exerciseDao.findOne(3));
        System.out.println(exerciseDao.findOne(4));
    }

}