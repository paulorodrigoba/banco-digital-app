package br.com.bancodigitaldoamazonas.api.service;

import br.com.bancodigitaldoamazonas.api.domain.cliente.Cliente;
import br.com.bancodigitaldoamazonas.api.domain.cliente.DadosCadastroCliente;
import br.com.bancodigitaldoamazonas.api.domain.cliente.DadosListaCliente;
import br.com.bancodigitaldoamazonas.api.domain.cliente.DadosUpdateCliente;
import br.com.bancodigitaldoamazonas.api.domain.endereco.Endereco;
import br.com.bancodigitaldoamazonas.api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public Cliente cadastrarCliente (DadosCadastroCliente dados){
        Cliente cliente = new Cliente(dados);
        return repository.save(cliente);
    }
    public List<DadosListaCliente> listaCliente() {
        return repository.findAll().stream()
                .map(DadosListaCliente::new)
                .collect(Collectors.toList());
    }
    public Optional<Cliente> buscarClientePorId(Long id) {
        return repository.findById(id);
    }
    public Cliente atualizarCliente(Long id, DadosUpdateCliente dados) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        cliente.setCpf(dados.cpf());
        cliente.setRg(dados.rg());
        cliente.setNome(dados.nome());
        cliente.setEmail(dados.email());
        cliente.setTelefone(dados.telefone());
        cliente.setEndereco(new Endereco(dados.endereco()));
        cliente.setAtivo(true);

        return repository.save(cliente);
    }
    public void deletarCliente(Long id) {
        repository.deleteById(id);
    }

}
