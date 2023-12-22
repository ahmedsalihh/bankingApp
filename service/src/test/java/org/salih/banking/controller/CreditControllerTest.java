package org.salih.banking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.salih.banking.model.Credit;
import org.salih.banking.model.CreditRequest;
import org.salih.banking.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CreditControllerTest {

    @Autowired
    private CreditController creditController;

    @MockBean
    private CreditService creditService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(creditController).build();
    }

    @Test
    void listCredits() throws Exception {
        List<Credit> creditList = new ArrayList<>();
        Credit c = new Credit();
        c.setInstallmentCount(3);
        c.setId(1);
        c.setAmount(BigDecimal.valueOf(10));
        creditList.add(c);

        Mockito.when(creditService.listCredits(Mockito.anyInt(), Mockito.anyInt(), Mockito.any(), Mockito.any())).thenReturn(creditList);

        mockMvc.perform(get("/credits/list").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void addCredit() throws Exception {
        Credit response = new Credit();
        response.setAmount(BigDecimal.valueOf(10));
        response.setInstallmentCount(3);

        CreditRequest request = new CreditRequest();
        request.setAmount(BigDecimal.valueOf(10));
        request.setInstallmentCount(3);
        request.setUserId(1l);

        Mockito.when(creditService.addCredit(Mockito.any())).thenReturn(response);

        mockMvc.perform(post("/credits/add").content(asJsonString(request)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void listCreditsByUserId() throws Exception {
        List<Credit> creditList = new ArrayList<>();
        Credit c = new Credit();
        c.setInstallmentCount(3);
        c.setId(1);
        c.setAmount(BigDecimal.valueOf(10));
        creditList.add(c);

        long userId = 1l;

        Mockito.when(creditService.listCreditsByUserId(Mockito.any())).thenReturn(creditList);

        mockMvc.perform(get("/credits/list/" + userId).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}