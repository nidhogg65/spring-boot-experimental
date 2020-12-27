package com.nidhogg.bank.spring.boot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    @Id
    private String id;
    private String username;
    private BigDecimal amount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm'Z'")
    private ZonedDateTime timestamp;
    private String reference;
    private String slogan;


    public Transaction(String username, BigDecimal amount, ZonedDateTime timestamp, String reference, String slogan) {
        this.username = username;
        this.amount = amount;
        this.reference = reference;
        this.timestamp = timestamp;
        this.slogan = slogan;
    }

}
