package br.com.bancodigitaldoamazonas.api.repository;

import br.com.bancodigitaldoamazonas.api.domain.conta.ContaSalario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaSalarioRepository extends JpaRepository<ContaSalario, Long> {
}
