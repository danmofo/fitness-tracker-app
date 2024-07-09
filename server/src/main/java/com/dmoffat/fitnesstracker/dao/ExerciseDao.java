package com.dmoffat.fitnesstracker.dao;

import com.dmoffat.fitnesstracker.model.Exercise;
import org.jooq.DSLContext;
import org.jooq.types.UInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.dmoffat.fitnesstracker.db.tables.Exercise.EXERCISE;

@Repository
public class ExerciseDao {
    @Autowired private DSLContext context;

    public List<Exercise> findAll() {
        return context
            .selectFrom(EXERCISE)
            .fetchInto(Exercise.class);
    }

    public Exercise findOne(Integer id) {
        return context
            .selectFrom(EXERCISE)
            .where(EXERCISE.ID.eq(UInteger.valueOf(id)))
            .fetchOneInto(Exercise.class);

    }
}
