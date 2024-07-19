package com.dmoffat.fitnesstracker.dao;

import com.dmoffat.fitnesstracker.db.tables.records.BodyWeightRecord;
import org.jooq.DSLContext;
import org.jooq.types.UInteger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.dmoffat.fitnesstracker.db.tables.BodyWeight.BODY_WEIGHT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BodyWeightDaoTest {
    @Autowired private DSLContext db;
    @Autowired private BodyWeightDao bodyWeightDao;

    @Test
    @Transactional
    void shouldCreateBodyWeightRecord() {
        Integer id = bodyWeightDao.create(1, 100.5);
        assertNotNull(id);

        var record = findBodyWeightRecord(id);

        assertNotNull(record);
        assertEquals(1, record.getUserId().intValue());
        assertEquals(100.5, record.getWeight());
    }

    private BodyWeightRecord findBodyWeightRecord(Integer id) {
        return db.selectFrom(BODY_WEIGHT)
            .where(BODY_WEIGHT.ID.eq(UInteger.valueOf(id)))
            .fetchOne();
    }

    @Test
    @Transactional
    void shouldFindIdByUserAndBodyWeight() {
        bodyWeightDao.create(1, 100.5);

        var foundId = bodyWeightDao.findIdByUserIdAndLoggedOn(1, LocalDate.now());
        assertNotNull(foundId);
    }

    @Test
    @Transactional
    void shouldUpdateWeightForBodyWeightId() {
        // Create a body weight record
        var id = bodyWeightDao.create(1, 100.0);

        bodyWeightDao.updateWeight(id, 50.0);

        var record = findBodyWeightRecord(id);
        assertEquals(50, record.getWeight());
    }
}