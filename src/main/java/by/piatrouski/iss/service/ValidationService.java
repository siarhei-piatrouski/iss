package by.piatrouski.iss.service;

import by.piatrouski.iss.model.dto.TransactionDto;

public interface ValidationService {

    void validateTransaction(TransactionDto transaction);

}
