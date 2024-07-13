package com.dmoffat.fitnesstracker.dao;

import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExerciseDaoTest {
    @Autowired private DSLContext db;
    @Autowired private ExerciseDao exerciseDao;

    @Test
    void shouldFindOneById() {
        var exercise = exerciseDao.findOne(1);

        assertNotNull(exercise);
        assertEquals(1, exercise.getId());
        assertEquals("Back squat", exercise.getName());
        assertNull(exercise.getBrand());
        assertEquals("FREE_WEIGHT", exercise.getType().toString());
    }
}