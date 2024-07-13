/*
 * This file is generated by jOOQ.
 */
package com.dmoffat.fitnesstracker.db;


import com.dmoffat.fitnesstracker.db.tables.SpringSession;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables in ft.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index SPRING_SESSION_SPRING_SESSION_IX2 = Internal.createIndex(DSL.name("SPRING_SESSION_IX2"), SpringSession.SPRING_SESSION, new OrderField[] { SpringSession.SPRING_SESSION.EXPIRY_TIME }, false);
    public static final Index SPRING_SESSION_SPRING_SESSION_IX3 = Internal.createIndex(DSL.name("SPRING_SESSION_IX3"), SpringSession.SPRING_SESSION, new OrderField[] { SpringSession.SPRING_SESSION.PRINCIPAL_NAME }, false);
}
