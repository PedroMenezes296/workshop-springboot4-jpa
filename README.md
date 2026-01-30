# Course API (Spring Boot + JPA/Hibernate)

API REST desenvolvida com **Spring Boot** e **JPA/Hibernate**, seguindo uma arquitetura em camadas (**Resource → Service → Repository**) e utilizando **H2** no perfil de testes para facilitar desenvolvimento e validação de endpoints.

## Objetivos do projeto
- Criar uma API REST com Spring Boot
- Implementar modelo de domínio com JPA/Hibernate
- Estruturar camadas lógicas (resource, service, repository)
- Configurar banco de dados de testes (H2) via profile
- Popular o banco automaticamente (seed)
- Implementar CRUD (Create, Retrieve, Update, Delete)
- Implementar tratamento de exceções

> Projeto inspirado no workshop do DevSuperior (Nelio Alves). :contentReference[oaicite:1]{index=1}

---

## Tecnologias
- Java (recomendado: 21+)
- Spring Boot (Web, Data JPA)
- Hibernate (ORM)
- H2 Database (perfil de testes)
- Maven
- Postman (testes de requisição)

---

## Modelo de domínio (visão geral)
Principais entidades do projeto (conforme workshop):
- `User`
- `Order`
- (evolução do workshop inclui `Product`, `Category`, `OrderItem`, `Payment` e `OrderStatus`) :contentReference[oaicite:2]{index=2}

---

## Arquitetura do projeto
Estrutura em camadas:
- **Resource Layer**: controllers REST
- **Service Layer**: regras de negócio
- **Repository Layer**: acesso ao banco (Spring Data JPA)
- **Entities**: modelo de domínio :contentReference[oaicite:3]{index=3}

---

## Como rodar o projeto

### 1) Pré-requisitos
- Java 21+ instalado e configurado no PATH
- Maven instalado (`mvn -v`)

### 2) Rodando com perfil de teste (H2)
No diretório do projeto:
```bash
mvn clean spring-boot:run -Dspring-boot.run.profiles=test
Se você usa IDE (Run as Spring Boot App), garanta que o profile ativo esteja como test.
Uma forma comum é manter no application.properties:

spring.profiles.active=test
(ou setar SPRING_PROFILES_ACTIVE=test na configuração de execução da IDE).

Banco H2 Console
Com o perfil test ativo, acesse:

Console: http://localhost:8080/h2-console

Configuração típica:

JDBC URL: jdbc:h2:mem:testdb

User: sa

Password: (vazio)

Endpoints (exemplos)
A API expõe endpoints REST (exemplos comuns do workshop):

Users
GET /users

GET /users/{id}

POST /users

PUT /users/{id}

DELETE /users/{id}

Orders
GET /orders

GET /orders/{id}

Observação importante sobre JSON:
Se houver relacionamento bidirecional (User ↔ Order), evite recursão infinita no retorno JSON.
A abordagem mais simples do workshop é usar @JsonIgnore em um dos lados do relacionamento.

Seed de dados (perfil test)
No perfil test, o banco pode ser populado automaticamente usando uma classe de configuração que implementa CommandLineRunner.
Exemplo típico:

cria usuários em memória

salva com userRepository.saveAll(...)

cria pedidos associados aos usuários

salva com orderRepository.saveAll(...) 
4e1bdd79-b40c-4d29-81f0-f518bcd…


Tratamento de exceções (padrão do projeto)
Tratamento de erros para:

Recurso não encontrado (ResourceNotFoundException)

Erros de integridade no banco (DatabaseException)

Resposta padronizada com StandardError e ResourceExceptionHandler
