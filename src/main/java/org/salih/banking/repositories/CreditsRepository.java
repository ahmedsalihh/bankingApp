package org.salih.banking.repositories;

import org.salih.banking.StatusEnum;
import org.salih.banking.model.Credit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CreditsRepository extends PagingAndSortingRepository<Credit, Long>, JpaRepository<Credit, Long> {
    List<Credit> findByUserId(Long userId);
    Page<Credit> findByCreatedAt(LocalDate createdAt, Pageable paging);
    Page<Credit> findByStatus(StatusEnum status, Pageable paging);
}
