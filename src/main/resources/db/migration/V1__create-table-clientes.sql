CREATE TABLE clientes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    rg VARCHAR (10) NOT NULL UNIQUE,
    telefone VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL,

    ativo BOOLEAN DEFAULT TRUE,


    logradouro VARCHAR(100) NOT NULL,
        bairro VARCHAR(50) NOT NULL,
        cep VARCHAR(10) NOT NULL,
        uf VARCHAR(2) NOT NULL,
        cidade VARCHAR(50) NOT NULL,
        numero VARCHAR(10),
        complemento VARCHAR(100)
);