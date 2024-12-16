package by.piatrouski.iss.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.piatrouski.iss.model.dto.TransactionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InvestigationSquadServiceImpl implements InvestigationSquadService {

    private final RabbitTemplate rabbitTemplate;
    private final TransactionService transactionService;
    private final HolderService holderService;
    private final AssetService assetService;

    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Override
    @Transactional
    public String createAndInvestigateTransaction(TransactionDto transaction) {
        if (!holderService.isHolderExists(transaction.getHolder().getId())) {
            throw new IllegalArgumentException("Holder not found");
        }
        if (!assetService.isAssetExists(transaction.getAsset().getId())) {
            throw new IllegalArgumentException("Asset not found");
        }

        TransactionDto pendingTransaction = transactionService.createTransaction(transaction);
        validateTransaction(pendingTransaction);
        return "Request is being processed";
    }

    @Override
    public TransactionDto getTransaction(Long transactionId) {
        return transactionService.getTransaction(transactionId);
    }

    private void validateTransaction(TransactionDto transaction) {
        log.info("Validate transaction {}", transaction);
        rabbitTemplate.convertAndSend(exchange, routingKey, transaction.getId());
    }

}
