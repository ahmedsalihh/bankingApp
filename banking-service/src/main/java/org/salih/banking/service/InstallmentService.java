package org.salih.banking.service;

import org.salih.banking.exception.NoInstallmentFoundException;
import org.salih.banking.model.Installment;
import org.salih.banking.model.PaymentRequest;

import java.util.List;

public interface InstallmentService {
    void pay(PaymentRequest paymentRequest) throws NoInstallmentFoundException;

    void calculateOverdue();

    List<Installment> listInstallmentsByCreditId(Long creditId);
}
