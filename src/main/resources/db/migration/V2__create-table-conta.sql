CREATE TABLE conta (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    numero_conta VARCHAR(10) NOT NULL,
    saldo DECIMAL(10, 2),
    data_criacao DATE,
    cliente_id BIGINT,
    tipo_conta VARCHAR(20),
    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);