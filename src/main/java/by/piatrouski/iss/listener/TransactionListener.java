package by.piatrouski.iss.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import by.piatrouski.iss.model.dto.TransactionDto;
import by.piatrouski.iss.service.TransactionService;
import by.piatrouski.iss.service.ValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionListener {

    private final ValidationService validationService;
    private final TransactionService transactionService;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void processTransaction(Long transactionId) {
        TransactionDto transaction = transactionService.getTransaction(transactionId);
        log.info("Transaction received {}", transaction);
        validationService.validateTransaction(transaction);
    }

}
