package by.piatrouski.iss.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import by.piatrouski.iss.model.dto.AssetDto;
import by.piatrouski.iss.model.dto.HolderDto;
import by.piatrouski.iss.model.dto.TransactionDto;

@ExtendWith(MockitoExtension.class)
class InvestigationSquadServiceTest {

    @Mock
    private RabbitTemplate mockRabbitTemplate;
    @Mock
    private TransactionService mockTransactionService;
    @Mock
    private HolderService mockHolderService;
    @Mock
    private AssetService mockAssetService;

    private InvestigationSquadServiceImpl sut;

    @BeforeEach
    void setUp() {
        sut = new InvestigationSquadServiceImpl(mockRabbitTemplate, mockTransactionService, mockHolderService,
                mockAssetService);
        ReflectionTestUtils.setField(sut, "exchange", "testExchange");
        ReflectionTestUtils.setField(sut, "routingKey", "testRoutingKey");
    }

    @Test
    void createAndInvestigateTransaction() {
        TransactionDto transaction = new TransactionDto();
        HolderDto holder = new HolderDto();
        holder.setId(0L);
        transaction.setHolder(holder);
        AssetDto asset = new AssetDto();
        asset.setId(0L);
        transaction.setAsset(asset);

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(0L);

        when(mockHolderService.isHolderExists(0L)).thenReturn(true);
        when(mockAssetService.isAssetExists(0L)).thenReturn(true);
        when(mockTransactionService.createTransaction(any())).thenReturn(transactionDto);

        String result = sut.createAndInvestigateTransaction(transaction);
        assertThat(result).isEqualTo("Request is being processed");
        verify(mockRabbitTemplate).convertAndSend("testExchange", "testRoutingKey", 0L);
    }

    @Test
    void createAndInvestigateTransaction_holderServiceReturnsFalse() {
        TransactionDto transaction = new TransactionDto();
        HolderDto holder = new HolderDto();
        holder.setId(0L);
        transaction.setHolder(holder);

        when(mockHolderService.isHolderExists(0L)).thenReturn(false);

        assertThatThrownBy(() -> sut.createAndInvestigateTransaction(transaction))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createAndInvestigateTransaction_assetServiceReturnsFalse() {
        TransactionDto transaction = new TransactionDto();
        HolderDto holder = new HolderDto();
        holder.setId(0L);
        transaction.setHolder(holder);
        AssetDto asset = new AssetDto();
        asset.setId(0L);
        transaction.setAsset(asset);

        when(mockHolderService.isHolderExists(0L)).thenReturn(true);
        when(mockAssetService.isAssetExists(0L)).thenReturn(false);

        assertThatThrownBy(() -> sut.createAndInvestigateTransaction(transaction))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getTransaction() {
        when(mockTransactionService.getTransaction(0L)).thenReturn(new TransactionDto());

        sut.getTransaction(0L);
        verify(mockTransactionService).getTransaction(0L);
    }
}
