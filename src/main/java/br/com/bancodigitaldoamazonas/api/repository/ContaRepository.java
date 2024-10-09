package br.com.bancodigitaldoamazonas.api.repository;


import br.com.bancodigitaldoamazonas.api.domain.cliente.Cliente;
import br.com.bancodigitaldoamazonas.api.domain.conta.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    List<Conta> findByCliente(Cliente cliente);

}
