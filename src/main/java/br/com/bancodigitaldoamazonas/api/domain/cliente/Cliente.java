package br.com.bancodigitaldoamazonas.api.domain.cliente;

import br.com.bancodigitaldoamazonas.api.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;
    private String rg;
    private String nome;
    private String email;
    private String telefone;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Cliente (){}

    public Cliente(DadosCadastroCliente dados){
        this.ativo = true;
        this.cpf = dados.cpf();
        this.rg = dados.rg();
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());

    }


}
