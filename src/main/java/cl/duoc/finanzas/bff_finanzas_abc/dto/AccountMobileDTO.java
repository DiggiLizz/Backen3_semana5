package cl.duoc.finanzas.bff_finanzas_abc.dto;

import lombok.Data;

@Data // Esto es Lombok: genera getters y setters automáticamente
public class AccountMobileDTO {
    private String accountNumber;
    private String accountType;
    private Double availableBalance;
}
