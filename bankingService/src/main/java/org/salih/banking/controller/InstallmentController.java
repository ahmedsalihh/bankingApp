package org.salih.banking.controller;

import org.salih.banking.exception.NoInstallmentFoundException;
import org.salih.banking.model.PaymentRequest;
import org.salih.banking.service.InstallmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/installments")
public class InstallmentController {

    private final InstallmentService installmentService;

    @Autowired
    public InstallmentController(InstallmentService installmentService) {
        this.installmentService = installmentService;
    }

    @PostMapping("/pay")
    public void paySingleInstallment(@RequestBody PaymentRequest paymentRequest) throws NoInstallmentFoundException {
        installmentService.pay(paymentRequest);
    }

    @GetMapping("/calculateOverdue")
    public void calculateOverdue(){
        installmentService.calculateOverdue();
    }
}