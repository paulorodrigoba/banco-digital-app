package br.com.bancodigitaldoamazonas.api.repository;

import br.com.bancodigitaldoamazonas.api.domain.conta.ContaCorrente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Long> {
}
