CREATE TABLE transacao (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    conta_origem_id BIGINT NOT NULL,
    conta_destino_id BIGINT NOT NULL,
    data_hora DATETIME NOT NULL,
    tipo_transacao VARCHAR(50) NOT NULL,
    valor DECIMAL(15, 2) NOT NULL,
    FOREIGN KEY (conta_origem_id) REFERENCES contas(id),
    FOREIGN KEY (conta_destino_id) REFERENCES contas(id)
);
