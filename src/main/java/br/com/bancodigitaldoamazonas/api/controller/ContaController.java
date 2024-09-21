package br.com.bancodigitaldoamazonas.api.controller;

import br.com.bancodigitaldoamazonas.api.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/bdamazonas/contas")
public class ContaController {
    @Autowired
    private ContaService contaService;

    @PostMapping("/deposito/{idConta}")
    public ResponseEntity<Void> depositar(@PathVariable Long idConta, @RequestBody BigDecimal valor) {
        contaService.depositar(idConta, valor);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/saque/{idConta}")
    public ResponseEntity<Void> sacar(@PathVariable Long idConta, @RequestBody BigDecimal valor) {
        contaService.sacar(idConta, valor);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/saldo/{idConta}")
    public ResponseEntity<BigDecimal> consultarSaldo(@PathVariable Long idConta) {
        BigDecimal saldo = contaService.consultarSaldo(idConta);
        return ResponseEntity.ok(saldo);
    }
}
