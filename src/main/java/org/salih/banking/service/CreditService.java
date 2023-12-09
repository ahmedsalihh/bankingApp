package org.salih.banking.service;

import org.salih.banking.model.Credit;
import org.salih.banking.model.CreditRequest;

import java.util.List;

public interface CreditService {
    List<Credit> listCredits(int page, int pageSize);

    Credit addCredit(CreditRequest creditRequest);

    List<Credit> listCreditsByUserId(Long userId);
}
