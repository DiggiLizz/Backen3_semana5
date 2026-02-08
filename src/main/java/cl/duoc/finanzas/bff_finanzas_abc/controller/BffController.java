package cl.duoc.finanzas.bff_finanzas_abc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.finanzas.bff_finanzas_abc.domain.LegacyAccount;
import cl.duoc.finanzas.bff_finanzas_abc.dto.AccountAtmDTO;
import cl.duoc.finanzas.bff_finanzas_abc.dto.AccountMobileDTO;
import cl.duoc.finanzas.bff_finanzas_abc.dto.AccountWebDTO;
import cl.duoc.finanzas.bff_finanzas_abc.service.AccountService;

@RestController
@RequestMapping("/api/v1/accounts")
public class BffController {

    @Autowired
    private AccountService accountService;

    // --- CANAL MÓVIL  ---
    @PostMapping("/mobile")
    public List<AccountMobileDTO> getMobileAccounts(@RequestBody List<LegacyAccount> rawData) {
        return accountService.processAccounts(rawData);
    }

    @GetMapping("/mobile")
    public List<AccountMobileDTO> getMobileAccounts() {
        // Ahora que agregaste el método al Service, esto funcionará:
        return accountService.findAllMobileAccounts(); 
    }

    // --- CANAL WEB (Datos completos y complejos) ---
    @PostMapping("/web")
    public List<AccountWebDTO> getWebAccounts(@RequestBody List<LegacyAccount> rawData) {
        return accountService.processWebAccounts(rawData);
    }

    @GetMapping("/web")
    public List<AccountWebDTO> getWebAccounts() {
        // Este método llama a la lista dbWeb que creamos en el Service
        return accountService.findAllWebAccounts(); 
    }

    // --- CANAL ATM / SUCURSAL (Operaciones críticas y eficientes) ---
    @PostMapping("/atm")
    public List<AccountAtmDTO> getAtmAccounts(@RequestBody List<LegacyAccount> rawData) {
        return accountService.processAtmAccounts(rawData);
    }

    @GetMapping("/atm")
    public List<AccountAtmDTO> getAtmAccounts() {
        // Este método llama a la lista dbAtm que creamos en el Service
        return accountService.findAllAtmAccounts(); 
    }
}
