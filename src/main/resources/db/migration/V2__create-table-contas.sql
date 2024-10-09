CREATE TABLE contas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    numero_conta VARCHAR(10) NOT NULL UNIQUE,  -- Deve ser única
    saldo DECIMAL(10, 2),
    data_criacao DATE,
    cliente_id BIGINT,
    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);
