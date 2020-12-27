package com.nidhogg.bank.spring.boot.repository;

import com.nidhogg.bank.spring.boot.model.Transaction;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends CrudRepository<Transaction, String> {

    @Query("SELECT * from TRANSACTION where username = :username")
    Iterable<Transaction> findAllByUsername(@Param("username") String username);
}
