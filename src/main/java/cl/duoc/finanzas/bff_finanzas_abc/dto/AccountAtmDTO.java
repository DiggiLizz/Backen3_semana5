package cl.duoc.finanzas.bff_finanzas_abc.dto;

import lombok.Data;

@Data
public class AccountAtmDTO {
    private String accountNumber;
    private Double availableBalance;
}