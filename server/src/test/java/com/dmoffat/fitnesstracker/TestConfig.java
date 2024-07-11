package com.dmoffat.fitnesstracker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionOperations;

@Configuration
public class TestConfig {

    /***
     * This disables Spring Session's transactional behaviour.
     *
     * Why do we need to do this?
     * When running tests with @Transactional, changes made by Spring Session are not rolled back, as the transaction
     * is performed with propagation set to {@link org.springframework.transaction.annotation.Propagation#REQUIRES_NEW}.
     *
     * This means that the transaction started during test method execution is suspended, a new transaction is opened,
     * Spring session does its thing, then the transaction is committed, meaning the changes it makes never get rolled
     * back. This results in a bunch of SPRING_SESSION records being created and left in the DB.
     */
    @Bean("springSessionTransactionOperations")
    public TransactionOperations springSessionTransactionOperations() {
        System.out.println("################################################");
        System.out.println("## Disabling transactions for Spring Session. ##");
        System.out.println("################################################");
        return TransactionOperations.withoutTransaction();
    }

}
