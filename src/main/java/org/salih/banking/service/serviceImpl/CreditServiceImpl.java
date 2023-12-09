package org.salih.banking.service.serviceImpl;

import org.salih.banking.StatusEnum;
import org.salih.banking.converter.InstallmentConverter;
import org.salih.banking.model.Credit;
import org.salih.banking.model.CreditRequest;
import org.salih.banking.model.Installment;
import org.salih.banking.model.User;
import org.salih.banking.repositories.InstallmentRepository;
import org.salih.banking.repositories.UserRepository;
import org.salih.banking.service.CreditService;
import org.salih.banking.repositories.CreditsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CreditServiceImpl implements CreditService {

    private final CreditsRepository creditsRepository;
    private final UserRepository userRepository;
    private final InstallmentRepository installmentRepository;

    @Autowired
    public CreditServiceImpl(CreditsRepository creditsRepository, UserRepository userRepository, InstallmentRepository installmentRepository) {
        this.creditsRepository = creditsRepository;
        this.userRepository = userRepository;
        this.installmentRepository = installmentRepository;
    }

    @Override
    public List<Credit> listCredits(int pageNo, int pageSize, StatusEnum status, LocalDate createdTime) {
        Page<Credit> pagedResult = null;
        Pageable paging = PageRequest.of(pageNo, pageSize);

        if (status == null && createdTime == null) {
            pagedResult = creditsRepository.findAll(paging);
        } else if (status == null) {
            pagedResult = creditsRepository.findByCreatedAt(createdTime, paging);
        } else if (createdTime == null) {
            pagedResult = creditsRepository.findByStatus(status, paging);
        }

        return pagedResult != null ? pagedResult.toList() : null;
    }

    @Override
    @Transactional
    public Credit addCredit(CreditRequest creditRequest) {
        User user = userRepository.findById(creditRequest.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        Credit credit = new Credit();
        credit.setStatus(StatusEnum.CREATED);
        credit.setAmount(creditRequest.getAmount());
        credit.setInstallmentCount(creditRequest.getInstallmentCount());
        credit.setUser(user);

        credit = creditsRepository.save(credit);

        List<Installment> installments = InstallmentConverter.generateInstallments(credit);

        installments.forEach(installmentRepository::save);
        credit.setInstallments(installments);

        return credit;
    }

    @Override
    public List<Credit> listCreditsByUserId(Long userId) {
        return creditsRepository.findByUserId(userId);
    }
}
