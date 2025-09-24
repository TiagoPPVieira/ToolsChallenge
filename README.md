# 🛠️ ToolsChallenge – A.R.Phoenix 🚀

<p align="center">
  <img src="https://logospng.org/download/sicredi/logo-sicredi-256.png" alt="Banco SICREDI" width="200"/>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://arphoenix.com.br/wp-content/uploads/2022/07/LOGO-CENTRAL-BLACK-RED.png" alt="A.R.Phoenix" width="200"/> 
</p>

---

## 📝 Descrição

Projeto desenvolvido como parte do **desafio técnico** da **A.R.Phoenix** em parceria com o **Banco Sicredi**, simulando cenários reais de **pagamentos, estornos e consultas de transações financeiras**.

---

## 📌 Sobre o Projeto

O **ToolsChallenge** é uma API REST que:
- Processa **pagamentos**.
- Realiza **estornos** de transações.
- Permite **consultas** de pagamentos já processados.

Além disso, o projeto contempla:
- ✅ **Testes unitários** com **Mockito / JUnit 5**.
- ✅ **Relatórios de cobertura** com **JaCoCo**.
- ✅ **Documentação automática** com **Swagger UI**.
- ✅ Uso do **H2 Database** em memória (não exigido, mas adicionado para simulação mais realista).

---

## ⚙️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **SpringDoc OpenAPI (Swagger UI)**
- **JUnit 5 / Mockito**
- **JaCoCo** (mínimo 75% de cobertura)
- **Docker**
- **H2 Database** (simulação em memória)

---

## 📖 Endpoints Principais

### 📌 Pagamento
**POST** `/api/payments`  
#### ➡️ Cria um pagamento  
✔️ **200 – Pagamento criado com sucesso**

```json
{
  "transacaoEntity": {
    "cartao": "string",
    "id": "string",
    "descricao": {
      "valor": "41.74",
      "dataHora": "2025-09-24T19:31:48.276Z",
      "estabelecimento": "string",
      "nsu": "string",
      "codigoAutorizacao": "string",
      "status": "string"
    },
    "formaPagamento": {
      "tipo": "AVISTA",
      "parcelas": "04"
    }
  }
}
```

### 📌 Estorno

**GET** `/api/estorno/{id}`
#### ➡️ Estorna um pagamento pelo ID
✔️ 200 – Pagamento estornado com sucesso

```json
{
  "cartao": "string",
  "id": "string",
  "descricao": {
    "valor": "string",
    "dataHora": "string",
    "estabelecimento": "string",
    "nsu": "string",
    "codigoAutorizacao": "string",
    "status": "string"
  },
  "formaPagamento": {
    "tipo": "AVISTA",
    "parcelas": "04"
  }
}
```

### 📌 Consultas

**GET** `/api/consulta`
#### ➡️ Lista todos os pagamentos
✔️ 200 – Pagamentos encontrados

```json
[
  {
    "cartao": "string",
    "id": "string",
    "descricao": {
      "valor": "string",
      "dataHora": "string",
      "estabelecimento": "string",
      "nsu": "string",
      "codigoAutorizacao": "string",
      "status": "string"
    },
    "formaPagamento": {
      "tipo": "AVISTA",
      "parcelas": "51"
    }
  }
]
```


**GET** `/api/consulta/{id}`
#### ➡️ Consulta um pagamento pelo ID
✔️ 200 – Pagamento encontrado

```json
{
  "cartao": "string",
  "id": "string",
  "descricao": {
    "valor": "string",
    "dataHora": "string",
    "estabelecimento": "string",
    "nsu": "string",
    "codigoAutorizacao": "string",
    "status": "string"
  },
  "formaPagamento": {
    "tipo": "AVISTA",
    "parcelas": "90"
  }
}
```

## 📊 Testes e Cobertura

1. Execute os testes com JaCoCo:
   ```bash
   mvn clean verify
2. Abra o relatório de cobertura:
    - Linux / Mac:
    ```bash
    open target/site/jacoco/index.html
    ```  
    - Windows (PowerShell):
    ```bash
    start target\site\jacoco\index.html

## 🔐 Observações Finais

* O desafio não exigia banco de dados, mas o H2 foi incluído para simular persistência em tempo de execução.

* A solução segue padrões de boas práticas REST e pode ser facilmente extendida para cenários em produção real com banco Oracle, Kafka, MongoDB ou AWS.

* Apliquei o Swagger nas controllers para documentar como fazer as requisições REST na api;

* Poderia ser adicionada autenticação como o JWT para robustez em produção.

---
