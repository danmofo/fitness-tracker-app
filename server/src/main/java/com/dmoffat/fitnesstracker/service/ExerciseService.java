package com.dmoffat.fitnesstracker.service;

import com.dmoffat.fitnesstracker.db.Tables;
import com.dmoffat.fitnesstracker.db.tables.records.ExerciseRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {
    private DSLContext dsl;

    @Autowired
    public ExerciseService(DSLContext dsl) {
        this.dsl = dsl;
    }

    public void printExercises() {
        Result<ExerciseRecord> exercises = dsl.selectFrom(Tables.EXERCISE).fetch();
        System.out.println(exercises);
    }
}
