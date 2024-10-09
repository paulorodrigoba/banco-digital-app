package br.com.bancodigitaldoamazonas.api.domain.transacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransferenciaRequest(
        @NotNull @NotBlank
        Long idContaOrigem,

        @NotNull @NotBlank
        Long idContaDestino,

        @NotNull @NotBlank
        BigDecimal valor
) {
}
