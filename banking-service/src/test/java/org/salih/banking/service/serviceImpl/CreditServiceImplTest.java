package org.salih.banking.service.serviceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.salih.banking.StatusEnum;
import org.salih.banking.model.Credit;
import org.salih.banking.model.CreditRequest;
import org.salih.banking.model.Installment;
import org.salih.banking.model.User;
import org.salih.banking.repositories.CreditsRepository;
import org.salih.banking.repositories.InstallmentRepository;
import org.salih.banking.repositories.UserRepository;
import org.salih.banking.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreditServiceImplTest {

    @Autowired
    private CreditService creditService;

    @MockBean
    private CreditsRepository creditsRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private InstallmentRepository installmentRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void listCreditsStatusAndCreateTimeNull() {
        Page<Credit> list = Mockito.mock(Page.class);

        Mockito.when(creditsRepository.findAll((Pageable) Mockito.any())).thenReturn(list);

        creditService.listCredits(0, 10, null, null);

        assertNotNull(list);
    }

    @Test
    void listCreditsStatusNull() {
        Page<Credit> list = Mockito.mock(Page.class);

        Mockito.when(creditsRepository.findByCreatedAt(Mockito.any(), Mockito.any())).thenReturn(list);

        creditService.listCredits(0, 10, null, LocalDate.now());

        assertNotNull(list);
    }

    @Test
    void listCreditsCreateTimeNull() {
        Page<Credit> list = Mockito.mock(Page.class);

        Mockito.when(creditsRepository.findByStatus(Mockito.any(), Mockito.any())).thenReturn(list);

        creditService.listCredits(0, 10, StatusEnum.PAID, null);

        assertNotNull(list);
    }

    @Test
    void addCredit() {
        User u = new User();
        u.setId(1l);
        u.setFirstname("test");
        u.setLastname("test");
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(u));

        Credit c = new Credit();
        c.setInstallmentCount(3);
        c.setAmount(BigDecimal.valueOf(10));
        c.setStatus(StatusEnum.CREATED);
        Mockito.when(creditsRepository.save(Mockito.any())).thenReturn(c);

        Mockito.when(installmentRepository.save(Mockito.any())).thenReturn(Mockito.mock(Installment.class));

        CreditRequest request = new CreditRequest();
        request.setUserId(1l);
        request.setAmount(BigDecimal.valueOf(10));
        request.setInstallmentCount(3);
        creditService.addCredit(request);

        assertEquals(c.getStatus(), StatusEnum.CREATED);
    }

    @Test
    void listCreditsByUserId() {
        List<Credit> creditList = new ArrayList<>();
        Credit c = new Credit();
        c.setInstallmentCount(3);
        c.setId(1);
        c.setAmount(BigDecimal.valueOf(10));
        creditList.add(c);

        Mockito.when(creditsRepository.findByUserId(Mockito.anyLong())).thenReturn(creditList);

        assertNotEquals(creditList.size(), 0);
    }
}