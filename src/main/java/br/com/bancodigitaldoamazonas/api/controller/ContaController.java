package br.com.bancodigitaldoamazonas.api.controller;

import br.com.bancodigitaldoamazonas.api.domain.conta.Conta;

import br.com.bancodigitaldoamazonas.api.domain.transacao.Transacao;
import br.com.bancodigitaldoamazonas.api.domain.transacao.TransferenciaRequest;
import br.com.bancodigitaldoamazonas.api.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/contas")
public class ContaController {

    @Autowired
    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    // 1. Criar uma nova conta
    @PostMapping("/criar")
    public ResponseEntity<?> criarConta(@RequestParam(required = false) Long clienteId) {
        if (clienteId == null) {
            return ResponseEntity.badRequest().body("O parâmetro 'clienteId' é obrigatório.");
        }

        try {
            Conta novaConta = contaService.criarConta(clienteId);
            return ResponseEntity.created(URI.create("/contas/" + novaConta.getId())).body(novaConta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    // 2. Depositar em uma conta
    @PostMapping("/{idConta}/deposito")
    public ResponseEntity<?> depositar(@PathVariable Long idConta, @RequestParam BigDecimal valor) {
        try {
            contaService.depositar(idConta, valor);
            return ResponseEntity.ok("Depósito realizado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 3. Sacar de uma conta
    @PostMapping("/{idConta}/saque")
    public ResponseEntity<?> sacar(@PathVariable Long idConta, @RequestParam BigDecimal valor) {
        try {
            contaService.sacar(idConta, valor);
            return ResponseEntity.ok("Saque realizado com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 4. Transferir via PIX
    @PostMapping("/transferencia")
    public ResponseEntity<?> transferirPix(@RequestBody TransferenciaRequest request) {
        try {
            contaService.transferirPix(request.idContaOrigem(), request.idContaDestino(), request.valor());
            return ResponseEntity.ok("Transferência PIX realizada com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 5. Consultar saldo
    @GetMapping("/{idConta}/saldo")
    public ResponseEntity<BigDecimal> consultarSaldo(@PathVariable Long idConta) {
        try {
            BigDecimal saldo = contaService.consultarSaldo(idConta);
            return ResponseEntity.ok(saldo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // 6. Consultar extrato bancário
    @GetMapping("/{idConta}/extrato")
    public ResponseEntity<List<Transacao>> extratoBancario(@PathVariable Long idConta) {
        try {
            List<Transacao> extrato = contaService.extratoBancario(idConta);
            return ResponseEntity.ok(extrato);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // 7. Listar contas por cliente
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Conta>> listarContasPorCliente(@PathVariable Long clienteId) {
        try {
            List<Conta> contas = contaService.listarContasPorCliente(clienteId);
            return ResponseEntity.ok(contas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}