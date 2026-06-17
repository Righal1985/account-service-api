package com.banca.account.service;

import com.banca.account.model.Account;
import com.banca.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.banca.account.exception.BusinessException;
import java.math.BigDecimal;

@Service
public class TransferService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public void transferir(String idOrigen, String idDestino, BigDecimal monto) {
        if (idOrigen.equals(idDestino)) {
            throw new BusinessException("No puedes realizar una transferencia a la misma cuenta");
        }

        Account origen = accountRepository.findById(idOrigen)
                .orElseThrow(() -> new BusinessException("Cuenta origen no encontrada"));

        Account destino = accountRepository.findById(idDestino)
                .orElseThrow(() -> new BusinessException("Cuenta destino no encontrada"));

        if (origen.getBalance().compareTo(monto) < 0) {
            throw new BusinessException("Fondos insuficientes");
        }


        if (origen.getBalance().compareTo(monto) < 0) {
            throw new BusinessException("Fondos insuficientes");
        }


        origen.setBalance(origen.getBalance().subtract(monto));
        destino.setBalance(destino.getBalance().add(monto));

        accountRepository.save(origen);
        accountRepository.save(destino);
    }
}
