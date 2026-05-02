<div align="center">

# 💰 FinanceFlow

### API REST de Gestão Financeira Pessoal

![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow)
![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)
![License](https://img.shields.io/badge/License-MIT-purple)

</div>

---

## 📖 Sobre o Projeto

O **FinanceFlow** é uma API REST de gestão financeira pessoal desenvolvida do zero com Java e Spring Boot.

O objetivo é resolver uma dor real: **o trabalho manual de registrar gastos**.

Com o FinanceFlow, o usuário cadastra suas receitas e despesas, visualiza seu saldo, acompanha gastos por categoria e recebe alertas sobre sua saúde financeira.

Cada usuário acessa **apenas os próprios dados**, com autenticação via JWT.

---

## 🛠️ Tecnologias

| Tecnologia | Versão | Uso |
|------------|--------|-----|
| Java | 21 | Linguagem principal |
| Spring Boot | 4.0 | Framework backend |
| Spring Data JPA | - | Persistência de dados |
| Spring Security | - | Segurança da aplicação |
| JWT (JJWT) | 0.12.6 | Autenticação stateless |
| PostgreSQL | 16 | Banco de dados |
| Hibernate | - | ORM |
| Lombok | - | Redução de boilerplate |
| BCrypt | - | Hash de senhas |
| Maven | - | Gerenciador de dependências |

---

## 📦 Estrutura do Projeto

```bash
src/
├── config/      → Configurações de segurança e filtro JWT
├── controller/  → Endpoints REST
├── dto/         → Records de entrada e saída
├── enums/       → Enumerações do sistema
├── exception/   → Exceções customizadas
├── model/       → Entidades JPA
├── repository/  → Acesso ao banco de dados
├── security/    → UserDetails e classes de segurança
└── service/     → Regras de negócio
```

🔐 Autenticação
A autenticação é feita via JWT.

Fluxo:
Cadastro em POST /usuarios
Login em POST /auth/login
Recebimento do token JWT
Uso do token nas rotas protegidas
Header esperado nas rotas protegidas: Authorization: Bearer seu_token_aqui

🌐 Endpoints da API

```bash
Públicos
Método	Endpoint	Descrição
POST	/usuarios	Criar usuário
POST	/auth/login	Realizar login
```
Protegidos (requerem token)
```bash
🙋 Meu perfil
Método	Endpoint	Descrição
GET	/me	Buscar meu perfil
PUT	/me	Atualizar meu perfil
DELETE	/me	Deletar minha conta

💸 Minhas transações
Método	Endpoint	Descrição
POST	/me/transacoes	Criar transação
GET	/me/transacoes	Listar transações
GET	/me/transacoes/{transacaoId}	Buscar por ID
PUT	/me/transacoes/{transacaoId}	Atualizar transação
DELETE	/me/transacoes/{transacaoId}	Deletar transação

📊 Meu dashboard
Método	Endpoint	Descrição
GET	/me/dashboard/saldo	Consultar saldo
GET	/me/dashboard/mensal	Resumo mensal
GET	/me/dashboard/categorias	Gastos por categoria
GET	/me/dashboard/alertas	Alertas financeiros
```

📋 Exemplos de Requisição

```bash
Criar usuário

POST /usuarios
Content-Type: application/json

JSON:

{
    "nomeCompleto": "Guilherme Souza",
    "email": "gui@email.com",
    "senha": "123456"
}

Login

POST /auth/login
Content-Type: application/json

JSON:

{
    "email": "gui@gmail.com",
    "senha": "123456"
}

Resposta esperada:

{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "type": "Bearer",
    "email": "gui@gmail.com",
    "nome": "Guilherme Souza"
}

Criar transação

POST /me/transacoes
Authorization: Bearer seu_token_aqui
Content-Type: application/json

JSON:

{
    "descricao": "Salário",
    "valor": 3000.00,
    "dataTransacao": "2026-05-01",
    "tipoTransacao": "RECEITA",
    "categoriaTransacao": "OUTROS"
}


Consultar saldo

GET /me/dashboard/saldo
Authorization: Bearer seu_token_aqui

JSON:

{
    "totalReceitas": 3000.00,
    "totalDespesas": 1000.00,
    "saldo": 2000.00
}

Alertas financeiros

GET /me/dashboard/alertas
Authorization: Bearer seu_token_aqui

JSON:

{
    "alertas": [
        "Atenção: Suas despesas estão maiores que suas receitas!",
        "Atenção: Nesse mês sua categoria com maior gasto foi -> MORADIA"
    ]
}

```

⚙️ Como Rodar Localmente
Pré-requisitos
Java 21
PostgreSQL
Maven

1. Clone o repositório
``` bash
git clone https://github.com/GuiSouzaH/Finance-Flow.git
cd Finance-Flow
```

2. Configure as variáveis de ambiente
``` bash
DATABASE_USERNAME=seu_usuario
DATABASE_PASSWORD=sua_senha
JWT_SECRET=sua_chave_secreta
JWT_EXPIRATION_MS=86400000

"JWT_EXPIRATION_MS é opcional. O valor padrão é 86400000 (24 horas)."
```

3. Crie o banco de dados
``` bash
CREATE DATABASE financeflow;
```
4. Rode a aplicação
``` bash
mvn spring-boot:run
```
5. Acesse
``` bash
http://localhost:8081
```

🚀 Roadmap 
``` bash
✅ FASE 1 — CRUD de usuários e transações
✅ FASE 2 — Dashboard financeiro
✅ FASE 3 — Autenticação com JWT
⏳ FASE 4 — IA (leitura de comprovantes via Google Gemini)
⏳ FASE 5 — Deploy em produção
⏳ FASE 6 — Frontend
```
🧠 Conceitos Aplicados
``` bash
✅ API REST com Spring Boot
✅ Arquitetura em camadas (Controller, Service, Repository)
✅ JPA/Hibernate com PostgreSQL
✅ DTOs com Records Java
✅ Stream API avançada (groupingBy, reducing, max)
✅ Optional para tratamento seguro de nulos
✅ Exceções customizadas com GlobalExceptionHandler
✅ BCrypt para hash de senhas
✅ Validações com Bean Validation (@Valid, @NotBlank, @Positive)
✅ @Transactional para consistência de dados
✅ Autenticação stateless com JWT
✅ Spring Security com filtro customizado
✅ Rotas protegidas por usuário autenticado
```

👨‍💻 Autor
``` bash
Desenvolvido por Guilherme Souza

LinkedIn: linkedin.com/in/guilherme-henrique-3545b9364
GitHub: github.com/GuiSouzaH
```

<div align="center">
⭐ Se curtiu o projeto ou acompanhou a evolução, deixa uma estrela!

</div> 





