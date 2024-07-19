package com.dmoffat.fitnesstracker.dao;

import com.dmoffat.fitnesstracker.model.UserBodyWeight;
import org.jooq.DSLContext;
import org.jooq.types.UInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

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

    public Integer findIdByUserIdAndLoggedOn(Integer userId, LocalDate loggedOn) {
        var id = db.selectFrom(BODY_WEIGHT)
            .where(BODY_WEIGHT.USER_ID.eq(UInteger.valueOf(userId)))
            .and(BODY_WEIGHT.LOGGED_ON.eq(loggedOn))
            .fetchOne(BODY_WEIGHT.ID);

        return id == null ? null : id.intValue();
    }

    public void updateWeight(Integer bodyWeightId, Double newBodyWeight) {
        db.update(BODY_WEIGHT)
            .set(BODY_WEIGHT.WEIGHT, newBodyWeight)
            .where(BODY_WEIGHT.ID.eq(UInteger.valueOf(bodyWeightId)))
            .execute();
    }

    public List<UserBodyWeight> findAll(Integer userId) {
        return db.selectFrom(BODY_WEIGHT)
            .where(BODY_WEIGHT.USER_ID.eq(UInteger.valueOf(userId)))
            .orderBy(BODY_WEIGHT.LOGGED_ON.desc())
            .fetchInto(UserBodyWeight.class);
    }
}
