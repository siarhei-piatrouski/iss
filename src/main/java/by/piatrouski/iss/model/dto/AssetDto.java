package by.piatrouski.iss.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssetDto {
    private Long id;
    private String name;
    private String isin;
    private String type;
    private LocalDate issueDate;
    private String country;

}
