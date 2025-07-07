# CRUD Client

Sistema de cadastro de clientes (CRUD) desenvolvido em Java com Spring Boot, PostgreSQL, Flyway e JPA.

## Sumário
- [Descrição](#descrição)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Configuração do Projeto](#configuração-do-projeto)
- [Modelo de Dados](#modelo-de-dados)
- [Migrations](#migrations)
- [Endpoints da API](#endpoints-da-api)
- [Exemplos de Requisição](#exemplos-de-requisição)
- [Validações](#validações)
- [Execução](#execução)
- [Referências](#referências)

---

## Descrição
Este projeto é uma API RESTful para gerenciamento de clientes, permitindo operações de cadastro, consulta, atualização e remoção (CRUD). Utiliza boas práticas de arquitetura, versionamento de banco de dados e validação de dados.

## Tecnologias Utilizadas
- Java 24
- Spring Boot 3.5.3
- Spring Data JPA
- Spring Validation
- PostgreSQL 17+ (recomendado 15 ou 16 para total compatibilidade)
- Flyway (migrations)
- Lombok
- Maven

## Configuração do Projeto

1. **Clone o repositório:**
   ```bash
   git clone <url-do-repo>
   cd crudClient
   ```
2. **Configure o banco de dados PostgreSQL:**
   - Crie um banco chamado `crudClient`.
   - Usuário padrão: `postgres`
   - Senha padrão: `leandro3cg`
   - Altere `src/main/resources/application.properties` se necessário:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/crudClient
     spring.datasource.username=postgres
     spring.datasource.password=leandro3cg
     ```
3. **Execute as migrations:**
   - O Flyway executa automaticamente ao iniciar a aplicação.

4. **Build do projeto:**
   ```bash
   ./mvnw clean install
   # ou no Windows
   mvnw.cmd clean install
   ```

5. **Execute a aplicação:**
   ```bash
   ./mvnw spring-boot:run
   # ou
   java -jar target/crudClient-0.0.1-SNAPSHOT.jar
   ```

---

## Modelo de Dados

### Entidade `Client`
| Campo      | Tipo      | Descrição                |
|------------|-----------|--------------------------|
| id         | UUID      | Identificador único      |
| name       | String    | Nome do cliente          |
| cpf        | String    | CPF (11 dígitos)         |
| income     | Double    | Renda                    |
| birthDate  | LocalDate | Data de nascimento       |
| children   | Integer   | Número de filhos         |

### Exemplo de JSON de entrada (ClientRequestDTO)
```json
{
  "name": "João da Silva",
  "cpf": "11146120789",
  "income": 3500.50,
  "birthDate": "2003-05-23",
  "children": 2
}
```

### Exemplo de JSON de saída (ClientResponseDTO)
```json
{
  "id": "uuid-gerado",
  "name": "João da Silva",
  "cpf": "11146120789",
  "income": 3500.50,
  "birthDate": "2003-05-23",
  "children": 2
}
```

---

## Migrations
O versionamento do banco é feito via Flyway:
- `V1__create-table-client.sql`: Criação da tabela `client`.
- `V2__Update-clients.sql`: Adiciona a coluna `name`.

---

## Endpoints da API

| Método | Endpoint           | Descrição                        |
|--------|--------------------|----------------------------------|
| GET    | /clients           | Lista paginada de clientes       |
| GET    | /clients/{id}      | Busca cliente por ID             |
| POST   | /clients           | Cria um novo cliente             |
| PUT    | /clients/{id}      | Atualiza um cliente existente    |
| DELETE | /clients/{id}      | Remove um cliente                |

### Parâmetros de paginação
- Os endpoints de listagem aceitam parâmetros padrão do Spring Data: `page`, `size`, `sort`.

---

## Exemplos de Requisição

### Criar cliente
```http
POST /clients
Content-Type: application/json

{
  "name": "João da Silva",
  "cpf": "11146120789",
  "income": 3500.50,
  "birthDate": "2003-05-23",
  "children": 2
}
```

### Atualizar cliente
```http
PUT /clients/{id}
Content-Type: application/json

{
  "name": "Leandro Marques",
  "cpf": "11146120789",
  "income": 4000.00,
  "birthDate": "2002-05-23",
  "children": 1
}
```

### Buscar cliente por ID
```http
GET /clients/{id}
```

### Listar clientes (paginado)
```http
GET /clients?page=0&size=10&sort=name,asc
```

### Remover cliente
```http
DELETE /clients/{id}
```

---

## Validações
- `name`: obrigatório, 2 a 50 caracteres
- `cpf`: obrigatório, 11 dígitos
- `birthDate`: não pode ser futura
- `income` e `children`: opcionais

---

## Execução e Testes
- Para rodar os testes:
  ```bash
  ./mvnw test
  ```
- Para acessar a API, utilize ferramentas como Postman, Insomnia ou curl.

---

## Referências
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Flyway](https://flywaydb.org/)
- [PostgreSQL](https://www.postgresql.org/)
- [Lombok](https://projectlombok.org/)

---

> Projeto desenvolvido para fins de estudo e demonstração de boas práticas em APIs REST Java. 