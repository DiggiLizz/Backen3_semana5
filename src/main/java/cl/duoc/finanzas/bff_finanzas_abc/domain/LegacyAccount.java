package cl.duoc.finanzas.bff_finanzas_abc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  // Necesario para que Spring pueda crear el objeto vacío primero
@AllArgsConstructor // Útil para crear objetos rápidamente en pruebas
public class LegacyAccount {
    private Integer id;
    private String fecha;
    private Double monto; 
    private String tipo;
}
