package br.com.bancodigitaldoamazonas.api.service;

import br.com.bancodigitaldoamazonas.api.domain.cliente.Cliente;
import br.com.bancodigitaldoamazonas.api.domain.conta.Conta;
import br.com.bancodigitaldoamazonas.api.domain.transacao.Transacao;
import br.com.bancodigitaldoamazonas.api.repository.ClienteRepository;
import br.com.bancodigitaldoamazonas.api.repository.ContaRepository;
import br.com.bancodigitaldoamazonas.api.repository.TransacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ContaService {

    @Autowired
    private final ContaRepository contaRepository;

    @Autowired
    private final ClienteRepository clienteRepository;

    @Autowired
    private final TransacaoRepository transacaoRepository;

    public ContaService(ContaRepository contaRepository, ClienteRepository clienteRepository, TransacaoRepository transacaoRepository) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
        this.transacaoRepository = transacaoRepository;
    }

    @Transactional
    public Conta criarConta(Long clienteId) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(clienteId);
        if (clienteOptional.isEmpty()) {
            throw new IllegalArgumentException("Cliente não encontrado!");
        }

        Cliente cliente = clienteOptional.get();
        Conta novaConta = new Conta();
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

        // Registrar transação
        Transacao transacao = new Transacao(null, conta, valor, "DEPÓSITO");
        transacaoRepository.save(transacao);

        contaRepository.save(conta);
    }

    public void sacar(Long idConta, BigDecimal valor) {
        Conta conta = contaRepository.findById(idConta)
                .orElseThrow(() -> new EntityNotFoundException("Conta não encontrada"));
        if (conta.getSaldo().compareTo(valor) >= 0) {
            conta.setSaldo(conta.getSaldo().subtract(valor));

            // Registrar transação
            Transacao transacao = new Transacao(conta, null, valor, "SAQUE");
            transacaoRepository.save(transacao);

            contaRepository.save(conta);
        } else {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
    }

    @Transactional
    public void transferirPix(Long idContaOrigem, Long idContaDestino, BigDecimal valor) {
        Conta contaOrigem = contaRepository.findById(idContaOrigem)
                .orElseThrow(() -> new EntityNotFoundException("Conta origem não encontrada"));
        Conta contaDestino = contaRepository.findById(idContaDestino)
                .orElseThrow(() -> new EntityNotFoundException("Conta destino não encontrada"));

        if (contaOrigem.getSaldo().compareTo(valor) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
        contaDestino.setSaldo(contaDestino.getSaldo().add(valor));

        // Registrar transação
        Transacao transacao = new Transacao(contaOrigem, contaDestino, valor, "PIX");
        transacaoRepository.save(transacao);

        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);
    }

    public List<Transacao> extratoBancario(Long idConta) {
        Conta conta = contaRepository.findById(idConta)
                .orElseThrow(() -> new EntityNotFoundException("Conta não encontrada"));

        return transacaoRepository.findByContaOrigemOrContaDestino(conta, conta);
    }

    public BigDecimal consultarSaldo(Long idConta) {
        Conta conta = contaRepository.findById(idConta)
                .orElseThrow(() -> new EntityNotFoundException("Conta não encontrada"));
        return conta.getSaldo();
    }

    public List<Conta> listarContasPorCliente(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        return contaRepository.findByCliente(cliente);
    }

}