# 🛠️ ToolsChallenge – A.R.Phoenix 🚀

<p align="center">
  <img src="https://logospng.org/download/sicredi/logo-sicredi-256.png" alt="Banco SICREDI" width="200"/>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://arphoenix.com.br/wp-content/uploads/2022/07/LOGO-CENTRAL-BLACK-RED.png" alt="A.R.Phoenix" width="200"/> 
</p>

---

## 📞 Contatos

👤 **Nome:** Tiago Peres Prestes Vieira

🔗 **LinkedIn:** [linkedin.com/in/tiago-peres-prestes-vieira](https://www.linkedin.com/in/tiago-peres-prestes-vieira/)

💻 **GitHub:** [github.com/TiagoPPVieira](https://github.com/TiagoPPVieira)

✉️ **E-mail:** [tiagoppvieira@gmail.com](mailto:tiagoppvieira@gmail.com)

📱 **Telefone / WhatsApp:** [+55 (53) 99177-3037](https://wa.me/5553991773037)

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
- **Spring Boot 3** (Web, Validation)
- **SpringDoc OpenAPI (Swagger UI)**
- **JUnit 5 / Mockito**
- **JaCoCo** (mínimo 75% de cobertura)
- **Docker**
- **H2 Database** (simulação em memória)

---

## 🌟 Diferenciais Abordados

- Boas práticas de **código limpo e seguro**
- Uso do **design pattern Strategy** como exemplo de abordagem arquitetural

---

## 📖 Documentação da API

- **Swagger UI:**  
  Após subir o projeto, acesse no navegador:  
  👉 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

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