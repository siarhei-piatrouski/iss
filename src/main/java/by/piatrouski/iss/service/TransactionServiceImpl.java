package by.piatrouski.iss.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.piatrouski.iss.mapper.TransactionMapper;
import by.piatrouski.iss.model.Transaction;
import by.piatrouski.iss.model.TransactionStatus;
import by.piatrouski.iss.model.dto.TransactionDto;
import by.piatrouski.iss.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;
    private final TransactionMapper mapper;

    @Override
    @Transactional
    public TransactionDto createTransaction(TransactionDto transaction) {
        log.info("Create new transaction : {}", transaction);
        Transaction newTransaction = mapper.fromTransactionDto(transaction);
        newTransaction.setStatus(TransactionStatus.PENDING);
        return mapper.fromTransactionEntity(repository.save(newTransaction));
    }

    @Override
    public TransactionDto getTransaction(Long transactionId) {
        return mapper.fromTransactionEntity(
                repository.getReferenceById(transactionId));
    }

    @Override
    @Transactional
    public void updateTransactionStatus(Long transactionId, TransactionStatus status) {
        repository.updateTransactionStatus(transactionId, status);
    }

}
