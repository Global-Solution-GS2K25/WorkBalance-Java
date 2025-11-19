# WorkBalance Hub API

API REST em Spring Boot para o projeto Global Solution - módulo Java Advanced.

## Como rodar localmente

1. Certifique-se de ter **Java 17** e **Maven** instalados.
2. No diretório do projeto, execute:

```bash
mvn spring-boot:run
```

3. Acesse:

- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- H2 Console: `http://localhost:8080/h2-console`

### Credenciais iniciais

Você deve primeiro registrar um usuário:

- `POST /api/auth/register`

Body exemplo:

```json
{
  "nome": "Admin",
  "email": "admin@workbalance.com",
  "senha": "123456",
  "role": "ADMIN"
}
```

Depois faça login:

- `POST /api/auth/login`

```json
{
  "email": "admin@workbalance.com",
  "senha": "123456"
}
```

Use o token retornado como **Bearer Token** no header `Authorization` para chamar os endpoints protegidos, como:

- `GET /api/usuarios`
- `POST /api/checkins`
- `GET /api/checkins?usuarioId=1&page=0&size=5`
```

## 📬 Collection Postman

A coleção para testes está em:

- `docs/postman/workbalance-collection.json`

Para usar, importe esse arquivo no Postman.
