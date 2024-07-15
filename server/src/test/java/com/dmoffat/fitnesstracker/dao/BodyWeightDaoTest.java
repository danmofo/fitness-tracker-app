package com.dmoffat.fitnesstracker.dao;

import org.jooq.DSLContext;
import org.jooq.types.UInteger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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

        var record = db.selectFrom(BODY_WEIGHT)
            .where(BODY_WEIGHT.ID.eq(UInteger.valueOf(id)))
            .fetchOne();

        assertNotNull(record);
        assertEquals(1, record.getUserId().intValue());
        assertEquals(100.5, record.getWeight());
    }
}