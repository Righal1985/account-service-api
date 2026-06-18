package com.banca.account.controller;

import com.banca.account.security.AuthService; // Asegúrate de crear esta clase
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Endpoint para registrar una contraseña en una cuenta existente
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String id, @RequestParam String password) {
        return ResponseEntity.ok(authService.register(id, password));
    }

    // Endpoint para loguearse y obtener el token
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String id, @RequestParam String password) {
        try {
            String token = authService.login(id, password);
            return ResponseEntity.ok(token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body("Error: " + e.getMessage());
        }
    }
}