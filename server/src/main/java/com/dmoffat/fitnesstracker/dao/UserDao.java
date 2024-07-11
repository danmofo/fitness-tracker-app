package com.dmoffat.fitnesstracker.dao;

import com.dmoffat.fitnesstracker.model.User;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.dmoffat.fitnesstracker.db.tables.User.USER;

@Repository
public class UserDao {
    @Autowired private DSLContext db;

    public User findByEmail(String email) {
        return db.selectFrom(USER)
            .where(USER.EMAIL.eq(email))
            .fetchOneInto(User.class);
    }
}
