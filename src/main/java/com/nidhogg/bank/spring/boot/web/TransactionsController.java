package com.nidhogg.bank.spring.boot.web;

import com.nidhogg.bank.spring.boot.dto.TransactionDto;
import com.nidhogg.bank.spring.boot.model.Transaction;
import com.nidhogg.bank.spring.boot.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TransactionsController {

    private final TransactionService transactionService;

    public TransactionsController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/transactions")
    public Iterable<Transaction> findAllTransactions() {
        return transactionService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/transactions/user/{userId}")
    public Iterable<Transaction> findAllTransactions(@PathVariable String userId) {
        return transactionService.findAllByUser(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/transactions")
    public Transaction createTransaction(@Valid @RequestBody TransactionDto transactionDto) {
        return transactionService.create(transactionDto.getUserId(), transactionDto.getAmount(), transactionDto.getReference());
    }

}
