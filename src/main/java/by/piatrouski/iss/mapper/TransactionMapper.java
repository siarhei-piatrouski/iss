package by.piatrouski.iss.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import by.piatrouski.iss.model.Transaction;
import by.piatrouski.iss.model.dto.TransactionDto;

@Mapper(componentModel = "spring", uses = HolderMapper.class)
public interface TransactionMapper {

    TransactionDto fromTransactionEntity(Transaction transaction);

    @Mapping(target = "id", ignore = true)
    Transaction fromTransactionDto(TransactionDto transaction);
}
