package br.com.bancodigitaldoamazonas.api.service;

import br.com.bancodigitaldoamazonas.api.domain.conta.Conta;
import br.com.bancodigitaldoamazonas.api.repository.ContaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    public void depositar(Long idConta, BigDecimal valor) {
        Conta conta = contaRepository.findById(idConta)
                .orElseThrow(() -> new EntityNotFoundException("Conta não encontrada"));
        conta.setSaldo(conta.getSaldo().add(valor));
        contaRepository.save(conta);
    }

    public void sacar(Long idConta, BigDecimal valor) {
        Conta conta = contaRepository.findById(idConta)
                .orElseThrow(() -> new EntityNotFoundException("Conta não encontrada"));
        if (conta.getSaldo().compareTo(valor) >= 0) {
            conta.setSaldo(conta.getSaldo().subtract(valor));
            contaRepository.save(conta);
        } else {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
    }

    public BigDecimal consultarSaldo(Long idConta) {
        Conta conta = contaRepository.findById(idConta)
                .orElseThrow(() -> new EntityNotFoundException("Conta não encontrada"));
        return conta.getSaldo();
    }
}

