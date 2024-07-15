package com.dmoffat.fitnesstracker.dao;

import com.dmoffat.fitnesstracker.model.WorkoutExercise;
import com.dmoffat.fitnesstracker.service.JsonMappingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jooq.DSLContext;
import org.jooq.JSON;
import org.jooq.Record;
import org.jooq.SelectConditionStep;
import org.jooq.types.UInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.dmoffat.fitnesstracker.db.tables.WorkoutExercise.WORKOUT_EXERCISE;

@Repository
public class WorkoutExerciseDao {
    @Autowired private ObjectMapper objectMapper;
    @Autowired private JsonMappingService jsonMappingService;
    @Autowired private DSLContext db;

    public Integer create(WorkoutExercise newExercise) {
        var record = db.newRecord(WORKOUT_EXERCISE);
        record.setExerciseId(UInteger.valueOf(newExercise.getExercise().getId()));
        record.setWorkoutId(UInteger.valueOf(newExercise.getWorkout().getId()));
        record.setWeight(newExercise.getWeight());
        record.setReps(newExercise.getReps());
        record.setSets(newExercise.getSets());
        record.setNotes(newExercise.getNotes());
        record.setEquipment(JSON.valueOf(jsonMappingService.writeToJson(newExercise.getEquipment())));
        record.setCreatedOn(LocalDateTime.now());
        record.store();

        return record.getId().intValue();
    }

    private String toQuotedStringList(List<String> list) {
        return list.stream()
            .map(item -> "\"" + item + "\"")
            .collect(Collectors.joining(","));
    }

    public WorkoutExercise findByWorkoutExerciseByWeightRepsAndEquipment(WorkoutExercise workoutExercise) {
        SelectConditionStep<Record> select = db.select()
            .from(WORKOUT_EXERCISE)
            .where(WORKOUT_EXERCISE.WORKOUT_ID.eq(UInteger.valueOf(workoutExercise.getWorkout().getId())))
            .and(WORKOUT_EXERCISE.WEIGHT.eq(workoutExercise.getWeight()))
            .and(WORKOUT_EXERCISE.REPS.eq(workoutExercise.getReps()));

        if(workoutExercise.getEquipment() != null) {
            select = select.and("equipment = JSON_ARRAY(" + toQuotedStringList(workoutExercise.getEquipment()) + ")");
        }

        return select.fetchOne(record -> {
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
