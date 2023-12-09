package org.salih.banking.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BankingUtils {
    private static final float INTREST_AMOUNT = 3;

    public static BigDecimal calculateOverdue(LocalDate today, LocalDate dueDate, BigDecimal amount) {
        long daysBetween = ChronoUnit.DAYS.between(dueDate, today);
        float overdueAmount = (daysBetween * (INTREST_AMOUNT / 100) * amount.floatValue()) / 360;
        return new BigDecimal(overdueAmount);
    }
}
