package com.dmoffat.fitnesstracker.dao;

import org.jooq.DSLContext;
import org.jooq.types.UInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import static com.dmoffat.fitnesstracker.db.tables.BodyWeight.BODY_WEIGHT;

@Repository
public class BodyWeightDao {
    @Autowired private DSLContext db;

    public Integer create(Integer userId, Double bodyWeight) {
        var record = db.newRecord(BODY_WEIGHT);
        record.setWeight(bodyWeight);
        record.setLoggedOn(LocalDate.now());
        record.setUserId(UInteger.valueOf(userId));
        record.store();

        return record.getId().intValue();
    }
}
