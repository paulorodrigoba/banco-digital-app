package br.com.bancodigitaldoamazonas.api.domain.conta;

import br.com.bancodigitaldoamazonas.api.domain.cliente.Cliente;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity (name = "contas")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroConta;
    private BigDecimal saldo;
    private LocalDate dataCriacao;

    @ManyToOne
    private Cliente cliente;
}