package br.com.bancodigitaldoamazonas.api.domain.conta;

import br.com.bancodigitaldoamazonas.api.domain.cliente.Cliente;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_conta")
public abstract class Conta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal saldo;
    private LocalDate dataCriacao;

    @ManyToOne
    private Cliente cliente;

    // Métodos de saque, depósito, etc.
}







