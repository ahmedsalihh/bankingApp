package org.salih.banking.service.serviceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.salih.banking.StatusEnum;
import org.salih.banking.exception.NoInstallmentFoundException;
import org.salih.banking.model.Installment;
import org.salih.banking.model.PaymentRequest;
import org.salih.banking.repositories.InstallmentRepository;
import org.salih.banking.service.InstallmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class InstallmentServiceImplTest {

    @Autowired
    private InstallmentService installmentService;

    @MockBean
    private InstallmentRepository installmentRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void paySuccess() throws NoInstallmentFoundException {
        Installment i = new Installment();
        i.setRemainingAmount(BigDecimal.valueOf(10));
        i.setTotalAmount(BigDecimal.valueOf(10));
        i.setStatus(StatusEnum.UNPAID);
        Mockito.when(installmentRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(i));
        Mockito.when(installmentRepository.save(Mockito.any())).thenReturn(i);

        PaymentRequest p = new PaymentRequest();
        p.setInstallmentId(1);
        p.setAmount(BigDecimal.valueOf(10));
        installmentService.pay(p);

        verify(installmentRepository, times(1)).save(i);
    }

    @Test
    void payFailed() throws NoInstallmentFoundException {
        Mockito.when(installmentRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        PaymentRequest p = new PaymentRequest();
        p.setInstallmentId(1);
        p.setAmount(BigDecimal.valueOf(10));

        NoInstallmentFoundException ex = assertThrows(NoInstallmentFoundException.class, () -> {
            installmentService.pay(p);
        });
    }

    @Test
    void calculateOverdue() {
        List<Installment> installments = new ArrayList<>();
        Installment i = new Installment();
        i.setDueDate(LocalDate.now().plusDays(2));
        i.setTotalAmount(BigDecimal.valueOf(10));
        i.setRemainingAmount(BigDecimal.valueOf(10));
        installments.add(i);

        Mockito.when(installmentRepository.findAll()).thenReturn(installments);
        Mockito.when(installmentRepository.save(Mockito.any())).thenReturn(Mockito.mock(Installment.class));

        installmentService.calculateOverdue();

        verify(installmentRepository, times(1)).save(i);
    }
}