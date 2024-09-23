# Banco Digital API

## Descrição

Este projeto é uma aplicação web desenvolvida em **Java** utilizando o **Spring Boot** e o **MySQL**, com o objetivo de criar uma **API REST** para um banco digital. A API permite que clientes criem contas (corrente, poupança, salário), realizem operações bancárias como **saque**, **depósito** e obtenham o **saldo disponível**.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.0** (Spring Web, Spring Data JPA, Spring Validation)
- **MySQL**
- **Flyway** (migrações de banco de dados)
- **Lombok** (para reduzir o código boilerplate)
- **Postman** (para testes de API)

## Funcionalidades

- **Criação de contas** (corrente, poupança e salário)
- **Depósito** em conta
- **Saque** de conta
- **Consulta de saldo disponível**

## Estrutura do Projeto

Abaixo está a estrutura de pacotes do projeto:

```
src/
 └── main/
     └── java/
         └── com/
             └── banco/
                 └── api/
                     ├── controller/         # Controladores da API (endpoints)
                     ├── domain/             # Entidades do sistema (Cliente, Conta)
                     ├── dto/                # Transferência de dados (entrada e saída)
                     ├── repository/         # Repositórios (interação com o banco de dados)
                     └── service/            # Lógica de negócios (operações de contas)
```

## Pré-requisitos

Para rodar este projeto em sua máquina, certifique-se de que você tenha:

- **Java 17** instalado
- **MySQL** instalado e rodando
- Uma ferramenta para testar APIs, como o **Postman**

## Configuração do Banco de Dados

1. Crie um banco de dados MySQL chamado `banco_digital`:
   ```sql
   CREATE DATABASE banco_digital;
   ```

2. No arquivo `src/main/resources/application.properties`, configure a conexão com o banco de dados:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/banco_digital
   spring.datasource.username=root
   spring.datasource.password=senha
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

3. As migrações do banco de dados serão aplicadas automaticamente via **Flyway**.

## Endpoints da API

### 1. Criar Cliente

**POST** `/clientes`

Exemplo de payload:
```json
{
  "nome": "João Silva",
  "cpf": "123.456.789-00",
  "endereco": "Rua ABC, 123",
  "telefone": "(92) 99999-9999"
}
```

### 2. Criar Conta

**POST** `/contas`

Exemplo de payload para conta corrente:
```json
{
  "tipo": "CORRENTE",
  "idCliente": 1
}
```

### 3. Depositar em Conta

**POST** `/contas/deposito/{idConta}`

Exemplo de payload:
```json
{
  "valor": 500.00
}
```

### 4. Sacar de Conta

**POST** `/contas/saque/{idConta}`

Exemplo de payload:
```json
{
  "valor": 200.00
}
```

### 5. Consultar Saldo

**GET** `/contas/saldo/{idConta}`

Exemplo de resposta:
```json
{
  "saldo": 300.00
}
```

## Como Rodar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/banco-digital-api.git
   ```

2. Navegue até o diretório do projeto:
   ```bash
   cd banco-digital-api
   ```

3. Compile e rode a aplicação:
   ```bash
   mvn spring-boot:run
   ```

A aplicação estará rodando em `http://localhost:8080`.

## Testando a API

Para testar os endpoints, use o **Postman** ou outra ferramenta de API. Você pode criar clientes, contas, realizar depósitos, saques e consultar o saldo disponível.

## Melhorias Futuras

- Adicionar autenticação e autorização (JWT)
- Histórico de transações por conta
- Suporte para múltiplas moedas
- Operações de transferência entre contas

## Contribuição

Se desejar contribuir com o projeto:

1. Faça um fork do projeto
2. Crie uma nova branch para sua feature (`git checkout -b feature/nova-feature`)
3. Faça o commit das suas alterações (`git commit -m 'Adiciona nova feature'`)
4. Envie para o repositório remoto (`git push origin feature/nova-feature`)
5. Crie um Pull Request

## Licença

Este projeto está licenciado sob a **MIT License**.
