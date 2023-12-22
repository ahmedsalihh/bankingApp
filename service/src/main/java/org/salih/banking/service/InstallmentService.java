package org.salih.banking.service;

import org.salih.banking.exception.NoInstallmentFoundException;
import org.salih.banking.model.PaymentRequest;

public interface InstallmentService {
    void pay(PaymentRequest paymentRequest) throws NoInstallmentFoundException;

    void calculateOverdue();
}
