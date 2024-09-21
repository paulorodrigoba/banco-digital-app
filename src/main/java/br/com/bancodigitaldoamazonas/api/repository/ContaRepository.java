package br.com.bancodigitaldoamazonas.api.repository;


import br.com.bancodigitaldoamazonas.api.domain.conta.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}
