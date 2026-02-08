package cl.duoc.finanzas.bff_finanzas_abc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cl.duoc.finanzas.bff_finanzas_abc.domain.LegacyAccount;
import cl.duoc.finanzas.bff_finanzas_abc.dto.AccountAtmDTO;
import cl.duoc.finanzas.bff_finanzas_abc.dto.AccountMobileDTO;
import cl.duoc.finanzas.bff_finanzas_abc.dto.AccountWebDTO;

@Service
public class AccountService {

    // Memorias temporales independientes para cada tipo de cliente
    private final List<AccountMobileDTO> dbMobile = new ArrayList<>();
    private final List<AccountWebDTO> dbWeb = new ArrayList<>();
    private final List<AccountAtmDTO> dbAtm = new ArrayList<>();

    // 1. LÓGICA MÓVIL: Respuestas ligeras, balance siempre positivo (Math.abs)
    public List<AccountMobileDTO> processAccounts(List<LegacyAccount> legacyData) {
        List<AccountMobileDTO> procesados = legacyData.stream()
            .map(legacy -> {
                AccountMobileDTO dto = new AccountMobileDTO();
                dto.setAccountNumber("ACC-" + legacy.getId());
                dto.setAccountType(legacy.getTipo() != null ? legacy.getTipo().toUpperCase() : "DESCONOCIDO");
                dto.setAvailableBalance(legacy.getMonto() != null ? Math.abs(legacy.getMonto()) : 0.0);
                return dto;
            }).collect(Collectors.toList());
        this.dbMobile.addAll(procesados);
        return procesados;
    }

    public List<AccountMobileDTO> findAllMobileAccounts() {
    return this.dbMobile;
}

    // 2. LÓGICA WEB: Datos completos (ID + Fecha de transacción)
    public List<AccountWebDTO> processWebAccounts(List<LegacyAccount> legacyData) {
        List<AccountWebDTO> procesados = legacyData.stream()
            .map(legacy -> {
                AccountWebDTO dto = new AccountWebDTO();
                dto.setId(legacy.getId());
                dto.setAccountNumber("WEB-" + legacy.getId());
                dto.setAccountType(legacy.getTipo() != null ? legacy.getTipo().toUpperCase() : "S/T");
                dto.setAvailableBalance(legacy.getMonto() != null ? legacy.getMonto() : 0.0);
                dto.setTransactionDate(legacy.getFecha());
                return dto;
            }).collect(Collectors.toList());
        this.dbWeb.addAll(procesados);
        return procesados;
    }

    public List<AccountWebDTO> findAllWebAccounts() {
        return this.dbWeb; 
    }

    public List<AccountWebDTO> findAllExistingAccounts() {
        return this.dbWeb; 
    }

    // 3. LÓGICA ATM/SUCURSAL: Eficiencia y seguridad operativa
    public List<AccountAtmDTO> processAtmAccounts(List<LegacyAccount> legacyData) {
        List<AccountAtmDTO> procesados = legacyData.stream()
            // Eficiencia: Solo procesamos cuentas con montos válidos y ID presente
            .filter(a -> a.getMonto() != null && a.getId() != null) 
            .map(legacy -> {
                AccountAtmDTO dto = new AccountAtmDTO();
                // Formato específico para terminales de sucursal
                dto.setAccountNumber("ATM-ID-" + legacy.getId());
                dto.setAvailableBalance(legacy.getMonto());
                return dto;
            }).collect(Collectors.toList());
        
        this.dbAtm.addAll(procesados);
        return procesados;
    }

    public List<AccountAtmDTO> findAllAtmAccounts() { 
        return this.dbAtm; 
    }
    
}