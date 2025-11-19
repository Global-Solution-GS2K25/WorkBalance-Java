# WorkBalance Hub API

API REST em **Spring Boot** para o projeto **Global Solution** – módulo **Java Advanced / Enterprise Application**.  
Ela funciona como **espinha dorsal** da solução, concentrando regras de negócio, autenticação e persistência dos dados de bem-estar dos colaboradores.

---

## 🧩 Visão Geral da Solução

A WorkBalance Hub API expõe endpoints REST para:

- Cadastro e autenticação de usuários;
- Registro de **check-ins de bem-estar** (humor, sono, estresse, sintomas físicos);
- Consulta de histórico de check-ins com **filtros e paginação**;
- Integração futura com:
  - Aplicativo / front-end;
  - Módulo de IoT (sensores);
  - Módulo de DevOps (deploy, observabilidade, etc.).

---

## 🎯 Objetivo da API

Fornecer uma **API centralizada e segura**, seguindo boas práticas de arquitetura, para:

- Padronizar o acesso aos dados de bem-estar;
- Facilitar integração entre times (Java, IoT, DevOps, Mobile);
- Garantir segurança com JWT;
- Viabilizar métricas e monitoramento de qualidade de vida no ambiente de trabalho.

---

## 🏗 Arquitetura em Camadas

A API segue uma arquitetura em camadas:

- **Controller (`api.controller`)**  
  Recebe as requisições HTTP e expõe os endpoints REST.

- **DTOs (`api.dto`)**  
  Objetos de transporte que modelam o que entra e o que sai da API.

- **Service (`service`)**  
  Contém as regras de negócio (validações, orquestrações, etc.).

- **Repository (`domain.repository`)**  
  Acesso ao banco de dados via **Spring Data JPA**.

- **Domain / Entities (`domain.entity`)**  
  Modelagem das tabelas/objetos de domínio: `Usuario`, `Equipe`, `CheckInBemEstar`.

- **Security (`security`)**  
  Implementação de autenticação JWT, filtro de requisições, integração com Spring Security.

- **Config (`config`)**  
  Configurações da aplicação (`SecurityConfig`, `OpenApiConfig`, etc.).

---

## 🛠 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.x**
- Spring Web (REST)
- Spring Data JPA (persistência)
- H2 Database (banco em memória para desenvolvimento)
- Spring Security
- JWT (JSON Web Token)
- Bean Validation (validações de entrada)
- Springdoc OpenAPI (Swagger)
- Postman (testes e documentação prática)

---

## 📦 Modelagem de Domínio (Entidades Principais)

- **Usuario**
  - `id`
  - `nome`
  - `email`
  - `senhaHash`
  - `role` (ex.: `ADMIN`, `USER`)
  - relacionamento com `Equipe`

- **Equipe**
  - `id`
  - `nome`
  - relação 1:N com `Usuario`

- **CheckInBemEstar**
  - `id`
  - `dataHora`
  - `humor` (escala numérica)
  - `nivelEstresse`
  - `qualidadeSono`
  - `sintomasFisicos`
  - `observacoes`
  - `usuarioId` (referência ao usuário que fez o check-in)

---

## ▶️ Como rodar localmente

1. Certifique-se de ter **Java 17** e **Maven** instalados.
2. No diretório do projeto, execute:

 ```bash
 mvn spring-boot:run
 ```
3. Acesse:
- Swagger UI (se disponível): ```http://localhost:8080/swagger-ui/index.html```
- H2 Console: ```http://localhost:8080/h2-console```
- JDBC URL: ```jdbc:h2:mem:workbalance-db```
- Usuário: ```sa```
- Senha: (em branco)

---

## 🔐 Segurança & Autenticação (JWT)

Fluxo básico
1. Registrar usuário

    Endpoint público:

    ```POST /api/auth/register```

    Body exemplo:
    ```bash
    {
    "nome": "Admin",
    "email": "admin@workbalance.com",
    "senha": "123456",
    "role": "ADMIN"
    }
    ```

2. Login e obtenção do token

    ```POST /api/auth/login```
    ```bash
    {
    "email": "admin@workbalance.com",
    "senha": "123456"
    }
    ```
    Resposta (exemplo):
    ```bash
    {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "tipo": "Bearer"
    }
    ```

3. Uso do token nas rotas protegidas

    Em todas as requisições protegidas, enviar o header:
    ```bash
    Authorization: Bearer SEU_TOKEN_AQUI
    ```

    Exemplos de rotas protegidas:

- ```GET /api/usuarios```
- ```POST /api/checkins```
- ```GET /api/checkins?usuarioId=1&page=0&size=5```

---

## 🌐 Endpoints Principais

**Autenticação**
- POST ```/api/auth/register```:
Registra um novo usuário.

- POST ```/api/auth/login```:
Autentica o usuário e retorna um token JWT.

**Usuários**
- GET ```/api/usuarios``` (protegido):
Lista os usuários cadastrados.

**Check-ins de Bem-estar**
- POST ```/api/checkins``` (protegido):
Registra um novo check-in.

Body exemplo:
```bash
{
  "usuarioId": 1,
  "humor": 4,
  "nivelEstresse": 2,
  "qualidadeSono": 5,
  "sintomasFisicos": "Leve dor de cabeça",
  "observacoes": "Semana tranquila de trabalho"
}
```

- **GET** ```/api/checkins?usuarioId=1&page=0&size=5``` (protegido): 
    Retorna uma página de check-ins do usuário informado.

    Resposta exemplo (estrutura do ```Page``` do Spring):
```bash
{
  "content": [
    {
      "id": 1,
      "usuarioId": 1,
      "dataHora": "2025-11-19T16:20:41.595744",
      "humor": 4,
      "nivelEstresse": 2,
      "qualidadeSono": 5,
      "sintomasFisicos": "Leve dor de cabeça",
      "observacoes": "Semana tranquila de trabalho"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 5,
    "paged": true
  },
  "totalElements": 1,
  "totalPages": 1,
  "first": true,
  "last": true
}
```

---

## 📊 Paginação, Ordenação e Filtros

- A listagem de check-ins utiliza Spring Data Pageable.
- Parâmetros suportados em ```/api/checkins```:
    - ```usuarioId``` – obrigatório, filtra os check-ins de um usuário específico;
    - ```page``` – número da página (0-based);
    - ```size``` – quantidade de registros por página.
Caso necessário, é possível estender para suporte a sort no futuro.

## 🧪 Testes com Postman

**📬 Collection Postman**
A coleção para testes está em:
- ```docs/postman/workbalance-collection.json```

Para usar:
1. Abra o Postman.
2. Clique em Import.
3. Selecione o arquivo ```workbalance-collection.json```.
4. Siga o fluxo:
- ```Registrar Usuário```
- ```Login (obter token)```
- Usar o token em:
    - ```Listar Usuários```
    - ```Criar Check-In```
    - ```Listar Check-Ins (paginado)```