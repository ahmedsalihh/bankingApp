package org.salih.banking.service.servıceImpl;

import org.salih.banking.model.Credit;
import org.salih.banking.service.CreditService;
import org.salih.banking.service.repositories.CreditsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditServiceImpl implements CreditService {

    private final CreditsRepository creditsRepository;

    @Autowired
    public CreditServiceImpl(CreditsRepository creditsRepository) {
        this.creditsRepository = creditsRepository;
    }

    @Override
    public List<Credit> listCredits(int pageNo, int pageSize) {
        Page<Credit> pagedResult;
        Pageable paging = PageRequest.of(pageNo, pageSize);

        pagedResult = creditsRepository.findAll(paging);
        return pagedResult.toList();
    }
}
