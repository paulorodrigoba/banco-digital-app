package br.com.bancodigitaldoamazonas.api.domain.transacao;

import br.com.bancodigitaldoamazonas.api.domain.conta.Conta;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Conta contaOrigem;

    @ManyToOne
    private Conta contaDestino;

    private BigDecimal valor;
    private String tipoTransacao; // Exemplo: "DEPÓSITO", "SAQUE", "PIX", "TRANSFERÊNCIA"
    private LocalDateTime dataHora;

    public Transacao() {
    }

    public Transacao(Conta contaOrigem, Conta contaDestino, BigDecimal valor, String tipoTransacao) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.tipoTransacao = tipoTransacao;
        this.dataHora = LocalDateTime.now();
    }
}
