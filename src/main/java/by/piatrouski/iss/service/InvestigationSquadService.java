package by.piatrouski.iss.service;

import by.piatrouski.iss.model.dto.TransactionDto;

public interface InvestigationSquadService {

    String createAndInvestigateTransaction(TransactionDto request);

    TransactionDto getTransaction(Long transactionId);

}
