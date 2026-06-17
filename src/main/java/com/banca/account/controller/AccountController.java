package com.banca.account.controller;

import com.banca.account.model.Account;
import com.banca.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    // Tu endpoint original de prueba
    @GetMapping("/test")
    public String checkSystem() {
        return "Conexión exitosa: El motor bancario está listo.";
    }

    // Listar todas las cuentas
    @GetMapping
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // Crear una nueva cuenta
    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountRepository.save(account);
    }
}