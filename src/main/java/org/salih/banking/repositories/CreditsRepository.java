package org.salih.banking.repositories;

import org.salih.banking.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditsRepository extends PagingAndSortingRepository<Credit, Long>, JpaRepository<Credit, Long> {
}
