package org.salih.banking.service.serviceImpl;

import org.salih.banking.StatusEnum;
import org.salih.banking.exception.NoInstallmentFoundException;
import org.salih.banking.model.Installment;
import org.salih.banking.model.PaymentRequest;
import org.salih.banking.repositories.InstallmentRepository;
import org.salih.banking.service.InstallmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Optional;

@Service
public class InstallmentServiceImpl implements InstallmentService {

    private final InstallmentRepository installmentRepository;

    @Autowired
    public InstallmentServiceImpl(InstallmentRepository installmentRepository) {
        this.installmentRepository = installmentRepository;
    }

    @Override
    public void pay(PaymentRequest paymentRequest) throws NoInstallmentFoundException {
        Optional<Installment> installment = installmentRepository.findById(paymentRequest.getInstallmentId());
        BigDecimal zeroValue = new BigDecimal("0.00");
        if (installment.isPresent()) {
            Installment ins = installment.get();
            BigDecimal remainingAmount = ins.getRemainingAmount().subtract(paymentRequest.getAmount());
            ins.setRemainingAmount(remainingAmount);
            if (ins.getRemainingAmount().compareTo(zeroValue) == 0) {
                ins.setStatus(StatusEnum.PAID);
            }
            installmentRepository.save(ins);
        } else {
            throw new NoInstallmentFoundException("There is no such an Installment!!");
        }
    }
}
