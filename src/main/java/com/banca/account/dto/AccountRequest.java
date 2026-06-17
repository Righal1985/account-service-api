package com.banca.account.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record AccountRequest(
        @NotBlank(message = "El número de cuenta es obligatorio")
        String accountNumber,

        @NotBlank(message = "El nombre del propietario es obligatorio")
        String ownerName,

        @NotNull(message = "El saldo inicial es obligatorio")
        @DecimalMin(value = "0.0", message = "El saldo no puede ser negativo")
        BigDecimal balance
) {}
