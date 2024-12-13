package by.piatrouski.iss.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HolderDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate birthDay;
    @JsonIgnore
    private boolean isFraudster;

}
