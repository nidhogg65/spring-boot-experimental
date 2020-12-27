package com.nidhogg.bank.spring.boot.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class TransactionDto {

    @NotBlank
    private String userId;

    @NotNull
    private BigDecimal amount;

    @NotBlank
    private String reference;

}
