package org.salih.banking.converter;

import org.salih.banking.model.Credit;
import org.salih.banking.model.Installment;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class InstallmentConverter {
    public static List<Installment> generateInstallments(Credit credit) {
        List<Installment> installments = new ArrayList<>();
        LocalDateTime initialDate = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MIN);

        MathContext mathContext = new MathContext(2, RoundingMode.HALF_UP);
        BigDecimal singleInstallmentAmount = credit.getAmount().divide(BigDecimal.valueOf(credit.getInstallmentCount()), mathContext);

        for (int i = 0; i < credit.getInstallmentCount(); i++) {
            LocalDateTime dueDate = getNextDueDate(initialDate);
            initialDate = dueDate;
            Installment ins = new Installment();
            ins.setTotalAmount(singleInstallmentAmount);
            ins.setRemainingAmount(singleInstallmentAmount);
            ins.setStatus("unpaid");
            ins.setDueDate(dueDate);
            ins.setCredit(credit);
            installments.add(ins);
        }
        return installments;
    }

    private static LocalDateTime getNextDueDate(LocalDateTime initialDate) {
        LocalDateTime nextDate = initialDate.plusMonths(1);

        return checkIfWeekend(nextDate);
    }

    private static LocalDateTime checkIfWeekend(LocalDateTime nextDate) {
        DayOfWeek dayOfWeek = nextDate.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY) {
            return nextDate.plusDays(2);
        } else {
            return nextDate.plusDays(1);
        }
    }
}
