package by.piatrouski.iss.service;

import by.piatrouski.iss.model.TransactionStatus;
import by.piatrouski.iss.model.dto.TransactionDto;

public interface TransactionService {

    TransactionDto createTransaction(TransactionDto transaction);

    TransactionDto getTransaction(Long transactionId);

    void updateTransactionStatus(Long transactionId, TransactionStatus status);
}
