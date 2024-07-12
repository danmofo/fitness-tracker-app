package com.dmoffat.fitnesstracker.dao;

import com.dmoffat.fitnesstracker.model.WorkoutExercise;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jooq.DSLContext;
import org.jooq.JSON;
import org.jooq.types.UInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static com.dmoffat.fitnesstracker.db.tables.WorkoutExercise.WORKOUT_EXERCISE;

@Repository
public class WorkoutExerciseDao {
    @Autowired private DSLContext db;

    public Integer create(WorkoutExercise newExercise) {
        try {
            var jsonMapper = new ObjectMapper();
            var record = db.newRecord(WORKOUT_EXERCISE);
            record.setExerciseId(UInteger.valueOf(newExercise.getExercise().getId()));
            record.setWorkoutId(UInteger.valueOf(newExercise.getWorkout().getId()));
            record.setWeight(UInteger.valueOf(newExercise.getWeight()));
            record.setReps(newExercise.getReps());
            record.setSets(newExercise.getSets());
            record.setNotes(newExercise.getNotes());
            record.setEquipment(JSON.valueOf(jsonMapper.writeValueAsString(newExercise.getEquipment())));
            record.setCreatedOn(LocalDateTime.now());
            record.store();

            return record.getId().intValue();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public WorkoutExercise findByWorkoutExerciseIdWeightRepsAndEquipment(WorkoutExercise workoutExercise) {
        return db.select()
            .from(WORKOUT_EXERCISE)
            .where(WORKOUT_EXERCISE.WORKOUT_ID.eq(UInteger.valueOf(workoutExercise.getWorkout().getId())))
            .and(WORKOUT_EXERCISE.WEIGHT.eq(UInteger.valueOf(workoutExercise.getWeight())))
            .and(WORKOUT_EXERCISE.REPS.eq(workoutExercise.getReps()))
            // todo: Check equipment
            .fetchOne(record -> {
                var duplicate = new WorkoutExercise();
                duplicate.setId(record.get(WORKOUT_EXERCISE.ID).intValue());
                duplicate.setSets(record.get(WORKOUT_EXERCISE.SETS));
                return duplicate;
            });
    }

    public void updateSets(Integer workoutExerciseId, Integer newSets) {
        db.update(WORKOUT_EXERCISE)
            .set(WORKOUT_EXERCISE.SETS, newSets)
            .set(WORKOUT_EXERCISE.UPDATED_ON, LocalDateTime.now())
            .where(WORKOUT_EXERCISE.ID.eq(UInteger.valueOf(workoutExerciseId)))
            .execute();
    }
}
