package com.banca.account.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @GetMapping("/test")
    public String checkSystem() {
        return "Conexión exitosa: El motor bancario está listo.";
    }
}