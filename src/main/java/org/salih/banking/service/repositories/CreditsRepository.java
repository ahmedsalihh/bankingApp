package org.salih.banking.service.repositories;

import org.salih.banking.model.Credit;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditsRepository extends PagingAndSortingRepository<Credit, Long> {
}
