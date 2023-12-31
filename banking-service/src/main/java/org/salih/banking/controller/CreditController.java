package org.salih.banking.controller;

import org.salih.banking.StatusEnum;
import org.salih.banking.model.Credit;
import org.salih.banking.model.CreditRequest;
import org.salih.banking.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/credits")
public class CreditController {

    private final CreditService creditService;

    @Autowired
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Credit>> listCredits(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) StatusEnum status,
            @RequestParam(required = false) LocalDate createdTime) {
        return ResponseEntity.ok(creditService.listCredits(pageNo, pageSize, status, createdTime));
    }

    @PostMapping("/add")
    public ResponseEntity<Credit> addCredit(@RequestBody CreditRequest creditRequest) {
        return ResponseEntity.ok(creditService.addCredit(creditRequest));
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<List<Credit>> listCreditsByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(creditService.listCreditsByUserId(userId));
    }
}
