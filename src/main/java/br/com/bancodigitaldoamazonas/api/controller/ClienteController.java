package br.com.bancodigitaldoamazonas.api.controller;

import br.com.bancodigitaldoamazonas.api.domain.cliente.Cliente;
import br.com.bancodigitaldoamazonas.api.domain.cliente.DadosCadastroCliente;
import br.com.bancodigitaldoamazonas.api.domain.cliente.DadosListaCliente;
import br.com.bancodigitaldoamazonas.api.domain.cliente.DadosUpdateCliente;
import br.com.bancodigitaldoamazonas.api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/bda/clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente (@RequestBody DadosCadastroCliente dados){
        Cliente novoCliente = service.cadastrarCliente(dados);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<DadosListaCliente>> listaClientes(){
        List<DadosListaCliente> clientes = service.listaCliente();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id){
        Optional<Cliente> professor = service.buscarClientePorId(id);
        return  professor.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody DadosUpdateCliente dadosAtualizados) {
        Cliente cliente = service.atualizarCliente(id, dadosAtualizados);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        service.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
