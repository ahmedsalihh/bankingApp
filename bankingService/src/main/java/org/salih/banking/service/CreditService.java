package org.salih.banking.service;

import org.salih.banking.StatusEnum;
import org.salih.banking.model.Credit;
import org.salih.banking.model.CreditRequest;

import java.time.LocalDate;
import java.util.List;

public interface CreditService {
    List<Credit> listCredits(int page, int pageSize, StatusEnum status, LocalDate createdTime);

    Credit addCredit(CreditRequest creditRequest);

    List<Credit> listCreditsByUserId(Long userId);
}
