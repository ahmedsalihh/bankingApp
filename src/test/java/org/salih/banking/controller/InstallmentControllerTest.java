package org.salih.banking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.salih.banking.model.PaymentRequest;
import org.salih.banking.service.InstallmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class InstallmentControllerTest {

    @Autowired
    private InstallmentController installmentController;

    @MockBean
    private InstallmentService installmentService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(installmentController).build();
    }

    @Test
    void paySingleInstallment() throws Exception {
        PaymentRequest request = new PaymentRequest();
        request.setInstallmentId(1);
        request.setAmount(BigDecimal.valueOf(10));

        doNothing().when(installmentService).pay(request);

        mockMvc.perform(post("/installments/pay").content(asJsonString(request)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        verify(installmentService, times(1)).pay(request);
    }

    @Test
    void calculateOverdue() throws Exception {
        doNothing().when(installmentService).calculateOverdue();

        mockMvc.perform(get("/installments/calculateOverdue").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        verify(installmentService, times(1)).calculateOverdue();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}