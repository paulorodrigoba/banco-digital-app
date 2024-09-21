package br.com.bancodigitaldoamazonas.api.repository;


import br.com.bancodigitaldoamazonas.api.domain.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}

