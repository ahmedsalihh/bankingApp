package org.salih.banking.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreditRequest {
    private Long userId;
    private BigDecimal amount;
    private int installmentCount;
}
