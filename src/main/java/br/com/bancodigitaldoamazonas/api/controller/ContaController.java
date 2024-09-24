package br.com.bancodigitaldoamazonas.api.controller;

import br.com.bancodigitaldoamazonas.api.domain.conta.Conta;
import br.com.bancodigitaldoamazonas.api.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/contas")
public class ContaController {

    @Autowired
    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping("/{tipo}")
    public ResponseEntity<Conta> criarConta(@PathVariable String tipo, @RequestBody CriarContaRequest request) {
        Conta novaConta = contaService.criarConta(request.getClienteId(), tipo);
        return ResponseEntity.ok(novaConta);
    }

    public static class CriarContaRequest {
        private Long clienteId;

        public Long getClienteId() {
            return clienteId;
        }

        public void setClienteId(Long clienteId) {
            this.clienteId = clienteId;
        }
    }


    @PostMapping("/{id}/deposito")
    public void depositar(@PathVariable Long id, @RequestParam BigDecimal valor) {
        contaService.depositar(id, valor);
    }

    @PostMapping("/{id}/saque")
    public void sacar(@PathVariable Long id, @RequestParam BigDecimal valor) {
        contaService.sacar(id, valor);
    }

    @GetMapping("/{id}/saldo")
    public BigDecimal consultarSaldo(@PathVariable Long id) {
        return contaService.consultarSaldo(id);
    }
}
