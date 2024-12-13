package by.piatrouski.iss.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import by.piatrouski.iss.model.TransactionStatus;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDto {
    private Long id;
    private String type;
    private TransactionStatus status;
    private BigDecimal amount;
    private HolderDto holder;
    private AssetDto asset;

}
