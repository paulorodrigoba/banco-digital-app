package br.com.bancodigitaldoamazonas.api.repository;

import br.com.bancodigitaldoamazonas.api.domain.conta.ContaPoupanca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaPoupancaRepository extends JpaRepository<ContaPoupanca, Long> {
}
