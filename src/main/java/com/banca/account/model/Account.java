package com.banca.account.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity // 👈 Hibernate sabe que esto es una tabla
@Table(name = "accounts") // Nombre de la tabla en DBeaver
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id // 👈 Define el identificador único (Primary Key)
    private String accountNumber;

    private String ownerName;
    private BigDecimal balance;
}