package com.nidhogg.bank.spring.boot.service;

import com.nidhogg.bank.spring.boot.model.Transaction;
import com.nidhogg.bank.spring.boot.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
@Transactional
public class TransactionService {

    private TransactionService self;

    private static final ZoneId DEFAULT_ZONE = ZoneId.systemDefault();

    private final TransactionRepository transactionRepository;

    private final String slogan;

    public TransactionService(TransactionRepository transactionRepository,
                              @Value(value = "${bank.slogan}") String slogan) {
        this.transactionRepository = transactionRepository;
        this.slogan = slogan;
    }

//    public TransactionService(TransactionService self, TransactionRepository transactionRepository,
//                              @Value(value = "${bank.slogan}") String slogan) {
//        this.self = self;
//        this.transactionRepository = transactionRepository;
//        this.slogan = slogan;
//    }

    public Transaction create(String username, BigDecimal amount, String reference) {
        printlnTransactionInfo();

        ZonedDateTime timestamp = LocalDateTime.now().atZone(DEFAULT_ZONE);
        Transaction newTransaction = new Transaction(username, amount, timestamp, reference, slogan);

        return transactionRepository.save(newTransaction);
    }

    public Iterable<Transaction> findAll() {
        printlnTransactionInfo();
        self.someTransactionalMethod();
        return transactionRepository.findAll();
    }

    public Iterable<Transaction> findAllByUser(String username) {
        printlnTransactionInfo();
        return transactionRepository.findAllByUsername(username);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void someTransactionalMethod() {
        printlnTransactionInfo();
        System.out.println("Another transactional method...");
    }

    private void printlnTransactionInfo() {
        System.out.printf("TransactionName: %s. Is a database transaction open? = %s\n",
                TransactionSynchronizationManager.getCurrentTransactionName(),
                TransactionSynchronizationManager.isActualTransactionActive());
    }

    @Autowired
    public void setSelf(TransactionService self) {
        this.self = self;
    }
}
