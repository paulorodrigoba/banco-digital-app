package br.com.bancodigitaldoamazonas.api.domain.cliente;

public record DadosListaCliente(
        Long id,
        String cpf,
        String nome,
        String email,
        String telefone
) {
    public DadosListaCliente(Cliente cliente){
        this(
                cliente.getId(),
                cliente.getCpf(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone()
        );
    }
}
