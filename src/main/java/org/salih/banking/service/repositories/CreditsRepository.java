package org.salih.banking.service.repositories;

import org.salih.banking.model.Credit;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CreditsRepository extends PagingAndSortingRepository<Credit, Long> {
}
