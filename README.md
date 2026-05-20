# Desafio ACC

[![Java](https://img.shields.io/badge/Java-21-blue?logo=java&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.6-brightgreen?logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-Container-blue?logo=docker&logoColor=white)](https://www.docker.com/)
[![SQL Server](https://img.shields.io/badge/SQL_Server-MSSQL-0078D6?logo=microsoft-sql-server&logoColor=white)](https://www.microsoft.com/en-us/sql-server)

## Visão geral

Aplicação Spring Boot (Java) para gerenciamento de Empresas e Fornecedores com relacionamento Many-to-Many. Este projeto inclui a configuração para rodar um banco SQL Server via Docker.

### Tecnologias
- Java 21
- Spring Boot 4.x
- Spring Data JPA
- Docker / Docker Compose
- Microsoft SQL Server

---

## Pré-requisitos

- Java 21
- Maven
- Docker & Docker Compose

## Como rodar (rápido)

1. Crie um arquivo `.env` na raiz (ou atualize) com as variáveis necessárias para o SQL Server. Exemplo mínimo:

```dotenv
SQL_URL=jdbc:sqlserver://mssql:1433;databaseName=accenture
SQL_DB=accenture
SQL_USER=sa
SQL_PASSWORD=YourStrong!Passw0rd
```

2. Inicie o SQL Server via Docker Compose (se você tiver `docker-compose.yml` configurado):

```bash
docker-compose up -d
```

3. Inicie a aplicação (Maven):

```bash
./mvnw spring-boot:run
```

ou empacote e rode o jar:

```bash
./mvnw -DskipTests package
java -jar target/desafio-acc-0.0.1-SNAPSHOT.jar
```

## Variáveis de configuração
O projeto consome `spring.datasource.*` via `application.properties` usando as variáveis do `.env` listadas acima (SQL_URL, SQL_USER, SQL_PASSWORD).

## Endpoints principais

Base: `/api`

- Empresas
  - POST   `/api/empresas`                      - criar empresa
    - Body (JSON):
      {
        "documento": "string",   // CNPJ (obrigatório)
        "fantasia": "string",    // nome fantasia (obrigatório)
        "cep": "string"          // CEP (obrigatório)
      }
    - Exemplo:

    ```json
    {
      "documento": "12345678000199",
      "fantasia": "Empresa Exemplo Ltda",
      "cep": "01001000"
    }
    ```

  - GET    `/api/empresas`                      - listar empresas
  - GET    `/api/empresas/{id}`                 - obter por id
  - PUT    `/api/empresas/{id}`                 - atualizar
    - Body (JSON): mesmo formato do POST (documento, fantasia, cep). Exemplo:

    ```json
    {
      "documento": "12345678000199",
      "fantasia": "Empresa Atualizada Ltda",
      "cep": "01002000"
    }
    ```

  - DELETE `/api/empresas/{id}`                 - excluir
  - POST   `/api/empresas/{id}/fornecedores`   - associar fornecedores (body: JSON array de ids de fornecedores)
    - Body (JSON): array de ids (Long). Exemplo:

    ```json
    [1, 2, 3]
    ```

  - GET    `/api/empresas/{id}/fornecedores`   - listar todos os fornecedores vinculados a uma empresa

- Fornecedores
  - POST   `/api/fornecedores`                 - criar fornecedor
    - Body (JSON):
      {
        "documento": "string",   // CPF ou CNPJ (obrigatório)
        "nome": "string",        // nome ou razão social (obrigatório)
        "email": "string",       // email (obrigatório)
        "cep": "string",         // CEP (obrigatório)
        "rg": "string",          // RG (opcional)
        "nascimento": "YYYY-MM-DD" // data (opcional)
      }
    - Exemplo:

    ```json
    {
      "documento": "12345678901",
      "nome": "Fulano de Tal",
      "email": "fulano@example.com",
      "cep": "01001000",
      "rg": "1234567",
      "nascimento": "1980-01-01"
    }
    ```

  - GET    `/api/fornecedores`                 - listar fornecedores
  - GET    `/api/fornecedores/{id}`            - obter por id
  - PUT    `/api/fornecedores/{id}`            - atualizar
    - Body (JSON): mesmo formato do POST (documento, nome, email, cep, rg opcional, nascimento opcional). Exemplo:

    ```json
    {
      "documento": "12345678901",
      "nome": "Fulano Atualizado",
      "email": "fulano.updated@example.com",
      "cep": "01002000",
      "rg": "1234567",
      "nascimento": "1980-01-01"
    }
    ```

  - DELETE `/api/fornecedores/{id}`            - excluir

Request/response usam DTOs (`EmpresaDto`, `FornecedorDto`) no pacote `com.accenture.desafio_acc.dto`.

## Exemplos rápidos
- Associar fornecedores a uma empresa (POST):
  - URL: `POST /api/empresas/5/fornecedores`
  - Body: JSON array de ids (Content-Type: application/json)

```json
[1, 2, 3]
```

- Listar fornecedores vinculados a uma empresa (GET):
  - URL: `GET /api/empresas/5/fornecedores`

## Observações úteis

- A relação Many-to-Many usa a tabela de junção `empresa_fornecedor`.
- Se a tabela de junção não for atualizada, verifique:
  - se você está atualizando e salvando o lado dono (`Empresa`) da relação;
  - se o método está anotado com `@Transactional` quando necessário;
  - habilite logs SQL em `application.properties` com `spring.jpa.show-sql=true` para inspecionar queries.