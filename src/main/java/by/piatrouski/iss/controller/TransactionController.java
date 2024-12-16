package by.piatrouski.iss.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.piatrouski.iss.model.dto.TransactionDto;
import by.piatrouski.iss.service.InvestigationSquadService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "transactions")
public class TransactionController {

    private final InvestigationSquadService investigationSquadService;

    @PostMapping
    public ResponseEntity<String> createTransaction(@RequestBody TransactionDto transaction) {
        return ResponseEntity.ok().body(investigationSquadService.createAndInvestigateTransaction(transaction));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getTransaction(@PathVariable(name = "id") Long transactionId) {
        return ResponseEntity.ok().body(investigationSquadService.getTransaction(transactionId));
    }
}
