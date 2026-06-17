package com.banca.account.controller;

import com.banca.account.dto.AccountRequest;
import com.banca.account.dto.AccountResponse;
import com.banca.account.model.Account;
import com.banca.account.repository.AccountRepository;
import com.banca.account.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountRequest request) {
        Account account = new Account();
        account.setAccountNumber(request.accountNumber());
        account.setOwnerName(request.ownerName());
        account.setBalance(request.balance());

        Account saved = accountRepository.save(account);

        return ResponseEntity.ok(new AccountResponse(
                saved.getAccountNumber(),
                saved.getOwnerName(),
                saved.getBalance()));
    }

    @GetMapping
    public List<AccountResponse> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(a -> new AccountResponse(a.getAccountNumber(), a.getOwnerName(), a.getBalance()))
                .collect(Collectors.toList());
    }

    @Autowired
    private TransferService transferService;

    @PostMapping("/transfer")
    public ResponseEntity<String> realizarTransferencia(
            @RequestParam String desde,
            @RequestParam String hacia,
            @RequestParam BigDecimal monto) {

        transferService.transferir(desde, hacia, monto);
        return ResponseEntity.ok("Transferencia realizada con éxito");
    }
}