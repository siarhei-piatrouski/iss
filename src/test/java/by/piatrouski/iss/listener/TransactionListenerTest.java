package by.piatrouski.iss.listener;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import by.piatrouski.iss.model.dto.TransactionDto;
import by.piatrouski.iss.service.TransactionService;
import by.piatrouski.iss.service.ValidationService;

@ExtendWith(MockitoExtension.class)
class TransactionListenerTest {

    @Mock
    private ValidationService mockValidationService;
    @Mock
    private TransactionService mockTransactionService;

    @InjectMocks
    private TransactionListener sut;

    @Test
    void processTransaction() {
        TransactionDto transaction = new TransactionDto();
        transaction.setId(0L);

        when(mockTransactionService.getTransaction(0L)).thenReturn(transaction);

        sut.processTransaction(0L);
        verify(mockValidationService).validateTransaction(transaction);
    }
}
