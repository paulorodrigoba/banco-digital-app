package br.com.bancodigitaldoamazonas.api.service;

import br.com.bancodigitaldoamazonas.api.domain.cliente.Cliente;
import br.com.bancodigitaldoamazonas.api.domain.conta.Conta;
import br.com.bancodigitaldoamazonas.api.domain.conta.ContaCorrente;
import br.com.bancodigitaldoamazonas.api.domain.conta.ContaPoupanca;
import br.com.bancodigitaldoamazonas.api.domain.conta.ContaSalario;
import br.com.bancodigitaldoamazonas.api.repository.ClienteRepository;
import br.com.bancodigitaldoamazonas.api.repository.ContaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

@Service
public class ContaService {
    @Autowired
    private final ContaRepository contaRepository;

    @Autowired
    private final ClienteRepository clienteRepository;

    public ContaService(ContaRepository contaRepository, ClienteRepository clienteRepository) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Conta criarConta(Long clienteId, String tipoConta) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(clienteId);
        if (clienteOptional.isEmpty()) {
            throw new IllegalArgumentException("Cliente não encontrado!");
        }

        Cliente cliente = clienteOptional.get();
        Conta novaConta;

        switch (tipoConta.toLowerCase()) {
            case "corrente":
                novaConta = new ContaCorrente();
                break;
            case "poupanca":
                novaConta = new ContaPoupanca();
                break;
            case "salario":
                novaConta = new ContaSalario();
                break;
            default:
                throw new IllegalArgumentException("Tipo de conta inválido!");
        }

        novaConta.setCliente(cliente);
        novaConta.setSaldo(BigDecimal.ZERO);
        novaConta.setDataCriacao(LocalDate.now());
        novaConta.setNumeroConta(gerarNumeroConta());

        return contaRepository.save(novaConta);
    }

    private String gerarNumeroConta() {
        Random random = new Random();
        int parte1 = random.nextInt(9999999);  // Gera o número de 7 dígitos
        int parte2 = random.nextInt(99);       // Gera o número de 2 dígitos
        return String.format("%07d-%02d", parte1, parte2);
    }

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

