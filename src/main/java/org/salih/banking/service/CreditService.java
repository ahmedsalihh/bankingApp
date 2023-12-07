package org.salih.banking.service;

import org.salih.banking.model.Credit;

import java.util.List;

public interface CreditService {
    List<Credit> listCredits(int page, int pageSize);
}
