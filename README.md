# 🌿 WorkBalance Hub API – Java Backend

API REST desenvolvida em **Spring Boot** para o projeto **Global Solution FIAP**, integrando o módulo de **Java Advanced** com o módulo de **Mastering Relational Database (Oracle)**.

A aplicação funciona como **espinha dorsal** da solução WorkBalance, concentrando regras de negócio, autenticação JWT, integração com procedures e functions PL/SQL e toda a lógica de bem-estar dos colaboradores.

---

## 🔎 Visão Geral da Solução

A WorkBalance Hub API oferece:

- Cadastro e autenticação de usuários (JWT)
- Registro de **check-ins de bem-estar** (humor, estresse, sono, sintomas)
- Consulta paginada de check-ins
- **Execução de procedures e funções Oracle**:
  - Inserção via `PR_CHECKIN_INS`
  - Cálculo via `FN_MEDIA_HUMOR_EQUIPE`
  - Classificação via `FN_INDICE_RISCO_EQUIPE`
- Base para integrações:
  - IoT (sensores de ambiente)
  - Mobile
  - DevOps
  - Front-end

---

## 🎯 Objetivos da API

- Centralizar regras e dados do WorkBalance
- Garantir segurança com JWT
- Utilizar **Oracle PL/SQL** como backend de cálculos e validações
- Facilitar integração com outros módulos da Global Solution
- Fornecer indicadores de saúde e bem-estar de equipes

---

# 🧱 Arquitetura em Camadas

### ✔ Controller (`api.controller`)
Gerencia as rotas HTTP da aplicação.

### ✔ DTOs (`api.dto`)
Modelos usados para entrada/saída de dados na API.

### ✔ Service (`service`)
Regras de negócio, validações e **chamadas ao Oracle** (procedures/functions).

### ✔ Repository (`repository`)
Acesso ao banco via JPA para consultas simples.

### ✔ Entities (`domain.entity`)
Representam as tabelas do banco.

### ✔ Security (`security`)
JWT, filtros e configuração de autorização.

### ✔ Config (`config`)
Configurações globais (Swagger, Beans, SecurityConfig, OpenAPI).

---

# 🛠 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.x**
- Spring Web
- Spring Security + JWT
- Spring Data JPA
- **Oracle Database 21c (FIAP Cloud)**
- PL/SQL — Procedures & Functions
- HikariCP (pool de conexões)
- Bean Validation
- Springdoc OpenAPI (Swagger)
- Postman

---

# 🗄 Integração com Oracle (PL/SQL)

A API consome **procedures** e **funções** do Oracle por meio do `DatabaseIntegrationService`.

### 📌 Procedures utilizadas:
- `PR_USUARIO_INS`
- `PR_CHECKIN_INS` (**usada no vídeo**)
- `PR_EQUIPES_INS`
- `PR_ESTACAO_INS`
- `PR_LEITURA_INS`
- `PR_PLANO_ACAO_INS`

### 📌 Funções utilizadas:
- `FN_PODE_FAZER_CHECKIN`
- `FN_MEDIA_HUMOR_EQUIPE`
- `FN_INDICE_RISCO_EQUIPE`

### 📁 Scripts (pasta `/database`)
- `01_create_workbalance.sql`
- `02_functions_workbalance.sql`
- `04_procedures_workbalance.sql`
- `05_inserts_workbalance.sql`

---

# 🧬 Endpoints Oracle (Procedures & Functions)

### 🟦 POST — Registrar check-in via procedure
`POST /api/db/checkins/procedure`

Exemplo:
```json
{
  "usuarioId": 12,
  "humor": 4,
  "nivelEstresse": 3,
  "qualidadeSono": 5,
  "sintomasFisicos": "cansaço leve",
  "observacoes": "check-in via procedure"
}
```

🟩 GET — Média de humor
```bash
GET /api/db/equipes/{idEquipe}/media-humor?dataInicio=2025-01-01T00:00:00&dataFim=2025-12-31T23:59:59
```
Exemplo de resposta:
```bash
3.5
```

🟧 GET — Índice de risco
```bash
GET /api/db/equipes/{idEquipe}/indice-risco
```
```bash
Resposta possível:
BAIXO | MEDIO | ALTO | SEM_DADOS
```

---

## 🔐 Segurança (JWT)

1️⃣ Registro
```arduino
POST /api/auth/register
```
Exemplo:
```json
{
  "nome": "Admin",
  "email": "admin@workbalance.com",
  "senha": "123456",
  "role": "ADMIN"
}
```

2️⃣ Login
```bash
POST /api/auth/login
```
Retorno:
```bash
{
  "token": "xxxx.yyyy.zzzz",
  "tipo": "Bearer"
}
```

3️⃣ Uso do token
Em rotas protegidas:
```makefile
Authorization: Bearer SEU_TOKEN
```

---

## 📚 Endpoints Gerais da Aplicação

👤 Usuários
- GET /api/usuarios
- POST /api/auth/register

😀 Check-ins (padrão)
- POST /api/checkins
- GET /api/checkins?usuarioId=1&page=0&size=5

🔢 Oracle (procedures & functions)
- POST /api/db/checkins/procedure
- GET /api/db/equipes/{id}/media-humor
- GET /api/db/equipes/{id}/indice-risco

---

## ▶️ Como Rodar o Projeto

1. Entre na pasta raiz e execute:
```bash
mvn spring-boot:run
```

2. Acesse:
- API base → http://localhost:8080
- Swagger → http://localhost:8080/swagger-ui/index.html (se habilitado)
- Banco Oracle configurado no application.properties

---

## 🧪 Testes com Postman

A coleção está em:
```bash
docs/postman/workbalance-collection.json
```

Fluxo sugerido:
1. Registrar usuário
2. Fazer login
3. Usar token nas rotas protegidas
4. Inserir check-in via procedure
5. Confirmar no Oracle
6. Consultar média de humor e índice de risco

---

## 🎥 Demonstração (vídeo entregue)
- O vídeo demonstra:
- Execução dos scripts Oracle
- Confirmação do banco
- API Java rodando
- Inserção via procedure
- Funções Oracle via API
- Validação final

LINK DO VÍDEO: 

---

## 👨‍💻 Autores

- MARIA EDUARDA FERNANDES ROCHA – RM 560657
- JUAN PABLO REBELO COELHO – RM 560445
- VICTOR DE CARVALHO ALVES - RM 560395

---

## ⭐ Status do Projeto
| Módulo            | Status       |
| ----------------- | ------------ |
| Banco Oracle      | ✔ Concluído  |
| Java Backend      | ✔ Concluído  |
| Integração PL/SQL | ✔ Concluído  |
| Testes Postman    | ✔ Concluído  |
| Vídeo             | ✔ Gravado    |
| Documentação      | ✔ Finalizada |
