package by.piatrouski.iss.service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import by.piatrouski.iss.model.TransactionStatus;
import by.piatrouski.iss.model.dto.HolderDto;
import by.piatrouski.iss.model.dto.TransactionDto;

@ExtendWith(MockitoExtension.class)
class ValidationServiceTest {

    @Mock
    private TransactionService mockTransactionService;

    @InjectMocks
    private ValidationServiceImpl sut;

    @Test
    void validateTransaction_holderIsFraudsterTrue() {
        TransactionDto transaction = new TransactionDto();
        transaction.setId(0L);
        transaction.setStatus(TransactionStatus.PENDING);
        HolderDto holder = new HolderDto();
        holder.setFraudster(true);
        transaction.setHolder(holder);

        sut.validateTransaction(transaction);
        verify(mockTransactionService).updateTransactionStatus(0L, TransactionStatus.DECLINED);
    }

    @Test
    void validateTransaction_holderIsFraudsterFalse() {
        TransactionDto transaction = new TransactionDto();
        transaction.setId(0L);
        transaction.setStatus(TransactionStatus.PENDING);
        HolderDto holder = new HolderDto();
        holder.setFraudster(false);
        transaction.setHolder(holder);

        sut.validateTransaction(transaction);
        verify(mockTransactionService).updateTransactionStatus(0L, TransactionStatus.APPROVED);
    }
}
