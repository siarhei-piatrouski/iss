package by.piatrouski.iss.service;

import static by.piatrouski.iss.model.TransactionStatus.PENDING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import by.piatrouski.iss.mapper.TransactionMapper;
import by.piatrouski.iss.model.Transaction;
import by.piatrouski.iss.model.dto.TransactionDto;
import by.piatrouski.iss.repository.TransactionRepository;
import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository mockRepository;
    @Mock
    private TransactionMapper mockMapper;

    @InjectMocks
    private TransactionServiceImpl sut;

    @Test
    void createTransaction() {
        Transaction transaction = new Transaction();
        transaction.setType("BUY");
        transaction.setAmount(BigDecimal.valueOf(10L));

        TransactionDto expectedResult = new TransactionDto();
        expectedResult.setType("BUY");
        expectedResult.setAmount(BigDecimal.valueOf(10L));

        when(mockMapper.fromTransactionDto(any())).thenReturn(transaction);
        when(mockRepository.save(any())).thenReturn(transaction);
        when(mockMapper.fromTransactionEntity(transaction)).thenReturn(expectedResult);

        TransactionDto result = sut.createTransaction(new TransactionDto());
        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    void getTransaction() {
        Transaction transaction = new Transaction();
        transaction.setId(0L);
        transaction.setType("BUY");
        transaction.setAmount(BigDecimal.valueOf(10L));

        TransactionDto expectedResult = new TransactionDto();
        transaction.setId(0L);
        expectedResult.setType("BUY");
        expectedResult.setAmount(BigDecimal.valueOf(10L));

        when(mockRepository.getReferenceById(0L)).thenReturn(transaction);
        when(mockMapper.fromTransactionEntity(any())).thenReturn(expectedResult);

        TransactionDto result = sut.getTransaction(0L);
        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    void updateTransactionStatus() {
        sut.updateTransactionStatus(0L, PENDING);

        verify(mockRepository).updateTransactionStatus(0L, PENDING);
    }
}
