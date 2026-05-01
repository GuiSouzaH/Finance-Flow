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

O **FinanceFlow** é um SaaS de gestão financeira pessoal desenvolvido do zero com Java e Spring Boot.

O objetivo é resolver uma dor real: **o trabalho manual de registrar gastos**.

Com o FinanceFlow, o usuário cadastra suas receitas e despesas, visualiza seu saldo em tempo real, acompanha gastos por categoria e recebe alertas inteligentes sobre sua saúde financeira.

**Próximas funcionalidades:**
- 🤖 Leitura automática de comprovantes via IA (Google Gemini)
- 🔐 Autenticação com JWT
- 📊 Dashboard completo
- 🚀 Deploy em produção

---

## 🛠️ Tecnologias

| Tecnologia | Versão | Uso |
|------------|--------|-----|
| Java | 21 | Linguagem principal |
| Spring Boot | 4.0 | Framework backend |
| Spring Data JPA | - | Persistência de dados |
| Spring Security | - | Segurança da aplicação |
| PostgreSQL | 16 | Banco de dados |
| Hibernate | - | ORM |
| Lombok | - | Redução de boilerplate |
| BCrypt | - | Criptografia de senhas |
| Maven | - | Gerenciador de dependências |

---

## 📦 Estrutura do Projeto

src/
├── config/ → Configurações (Security, etc)
├── controller/ → Endpoints REST
├── service/ → Regras de negócio
├── repository/ → Acesso ao banco de dados
├── model/ → Entidades JPA
├── dto/ → Records de entrada e saída
├── enums/ → Enumerações do sistema
└── exception/ → Exceções customizadas

---

## ⚙️ Como Rodar Localmente

### Pré-requisitos:
- Java 21
- PostgreSQL
- Maven

### 1. Clone o repositório:

git clone https://github.com/GuiSouzaH/Finance-Flow.git

### 2. Configure as variáveis de ambiente:

DATABASE_USERNAME=seu_usuario
DATABASE_PASSWORD=sua_senha

### 3. Crie o banco de dados:

CREATE DATABASE financeflow;

### 4. Rode a aplicação:

mvn spring-boot:run

### 5. Acesse:

http://localhost:8081

🔗 Endpoints da API

👤 Usuários

Método	  Endpoint	      Descrição
POST	  /usuarios	      Criar usuário
GET	    /usuarios	      Listar usuários
GET	    /usuarios/{id}	Buscar por ID
PUT	    /usuarios/{id}	Atualizar usuário
DELETE	/usuarios/{id}	Deletar usuário
💸 Transações

Método	  Endpoint	                                  Descrição
POST	  /usuarios/{id}/transacoes	                  Criar transação
GET	    /usuarios/{id}/transacoes	                  Listar transações
GET	    /usuarios/{id}/transacoes/{transacaoId}	    Buscar por ID
PUT	    /usuarios/{id}/transacoes/{transacaoId}	    Atualizar transação
DELETE	/usuarios/{id}/transacoes/{transacaoId}	    Deletar transação
GET	    /usuarios/{id}/transacoes/saldo	Calcular    saldo
📊 Dashboard
Método	  Endpoint	                                  Descrição
GET	    /usuarios/{id}/dashboard/mensal	            Resumo mensal
GET	    /usuarios/{id}/dashboard/categorias	        Gastos por categoria
GET	    /usuarios/{id}/dashboard/alertas	          Alertas financeiros

📋 Exemplos de Requisição

Criar Usuário:

POST /usuarios
{
    "nomeCompleto": "Guilherme Souza",
    "email": "gui@email.com",
    "senha": "123456"
}

Criar Transação:

POST /usuarios/{id}/transacoes
{
    "descricao": "Salário",
    "valor": 3000.00,
    "dataTransacao": "2026-04-01",
    "tipoTransacao": "RECEITA",
    "categoriaTransacao": "OUTROS"
}

Resposta do Saldo:

{
    "totalReceitas": 3000.00,
    "totalDespesas": 1000.00,
    "saldo": 2000.00
}

Resposta dos Alertas:

{
    "alertas": [
        "Atenção: Suas despesas estão maiores que suas receitas!",
        "Atenção: Nesse mês sua categoria com maior gasto foi -> MORADIA"
    ]
}

🚀 Roadmap

✅ FASE 1 - MVP (CRUD completo)
✅ FASE 2 - Dashboard financeiro
⏳ FASE 3 - Autenticação JWT
⏳ FASE 4 - IA (leitura de comprovantes)
⏳ FASE 5 - Deploy em produção
⏳ FASE 6 - Frontend

🧠 Conceitos Aplicados

✅ API REST com Spring Boot
✅ Arquitetura em camadas (Controller, Service, Repository)
✅ JPA/Hibernate com PostgreSQL
✅ DTOs com Records Java
✅ Stream API avançada (groupingBy, reducing, max)
✅ Optional para tratamento seguro de nulos
✅ Exceções customizadas com GlobalExceptionHandler
✅ BCrypt para criptografia de senhas
✅ Validações com Bean Validation (@Valid, @NotBlank, @Positive)
✅ @Transactional para consistência de dados

👨‍💻 Autor
Desenvolvido por Guilherme Souza

LinkedIn linkedin.com/in/guilherme-henrique-3545b9364/
GitHub https://github.com/GuiSouzaH

<div align="center">
⭐ Se esse projeto te ajudou, deixa uma estrela!

</div> ```
