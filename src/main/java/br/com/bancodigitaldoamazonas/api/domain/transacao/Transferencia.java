package br.com.bancodigitaldoamazonas.api.domain.transacao;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class Transferencia {

    @NotNull
    private Long idContaOrigem;

    @NotNull
    private Long idContaDestino;

    @NotNull
    private BigDecimal valor;

    // Construtor
    public Transferencia(TransferenciaRequest request) {
        this.idContaOrigem = idContaOrigem;
        this.idContaDestino = idContaDestino;
        this.valor = valor;
    }
}