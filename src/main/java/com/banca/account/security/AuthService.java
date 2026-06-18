package com.banca.account.security;

import com.banca.account.model.Account;
import com.banca.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String register(String id, String password) {
        // Buscamos la cuenta existente
        Account account = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        // Ciframos la contraseña antes de guardarla
        account.setPassword(passwordEncoder.encode(password));
        repository.save(account);

        return "Usuario registrado correctamente";
    }

    public String login(String id, String password) {
        Account account = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        // Comparamos la contraseña recibida con la cifrada en base de datos
        if (!passwordEncoder.matches(password, account.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        // Si todo es correcto, generamos el token
        return jwtService.generateToken(id);
    }
}
