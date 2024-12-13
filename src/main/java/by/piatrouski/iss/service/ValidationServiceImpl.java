package by.piatrouski.iss.service;

import static by.piatrouski.iss.model.TransactionStatus.APPROVED;
import static by.piatrouski.iss.model.TransactionStatus.DECLINED;
import static by.piatrouski.iss.model.TransactionStatus.PENDING;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.piatrouski.iss.model.TransactionStatus;
import by.piatrouski.iss.model.dto.TransactionDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidationServiceImpl implements ValidationService {

    private final TransactionService transactionService;

    @Override
    @Transactional
    public void validateTransaction(TransactionDto transaction) {
        if (PENDING.equals(transaction.getStatus())) {
            //Validation logic

            TransactionStatus transactionStatus = transaction.getHolder().isFraudster() ? DECLINED : APPROVED;
            transactionService.updateTransactionStatus(transaction.getId(), transactionStatus);
        }
    }
}
