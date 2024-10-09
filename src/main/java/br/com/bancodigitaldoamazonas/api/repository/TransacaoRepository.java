package br.com.bancodigitaldoamazonas.api.repository;

import br.com.bancodigitaldoamazonas.api.domain.conta.Conta;
import br.com.bancodigitaldoamazonas.api.domain.transacao.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByContaOrigemOrContaDestino(Conta contaOrigem, Conta contaDestino);
}
