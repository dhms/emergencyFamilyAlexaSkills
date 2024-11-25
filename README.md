# **Emergency Family Skill for Alexa**

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

**Emergency Family Skill** é uma skill para Amazon Alexa projetada para auxiliar em situações de emergência, permitindo que usuários notifiquem contatos rapidamente via SNS (Simple Notification Service) da AWS. Este projeto também é uma vitrine de integração de tecnologias AWS com habilidades em Java e boas práticas em desenvolvimento serverless.

---

## 🚀 **Funcionalidades**
- **Notificação instantânea via SNS**: Permite o envio de alertas para contatos cadastrados diretamente pela skill.
- **Integração Alexa-SNS**: Comunicação eficiente entre as interfaces da Alexa e os serviços AWS.
- **Logs detalhados no CloudWatch**: Para rastreamento e depuração de erros em tempo real.
- **Configuração via Terraform**: Gestão de infraestrutura como código para provisionamento da AWS Lambda e SNS (Não terminado ainda).

---

## 🛠 **Tecnologias Utilizadas**
- **Linguagem**: Java 11
- **AWS Services**: Lambda, SNS, S3, CloudWatch
- **Infraestrutura**: Terraform (Não terminado ainda)
- **CI/CD**: AWS CLI para deploy de código diretamente para o Lambda.

---

## 📂 **Estrutura do Projeto**

```plaintext
/src/main/java
├── core/
│   ├── model/
│   │   └── EmergencyRequest.java
│   ├── usecase/
│   └── gateway/
├── adapter/
│   ├── sns/
│   ├── alexa/
│   │   └── EventMapper.java
│   └── config/
└── lambda/
    └── handler/
        ├── EmergencyHandler.java
        └── FamilyHelpStreamHandler.java
```

---

## ⚡ **Deploy**

### Pré-requisitos
- **AWS CLI** configurado.
- Bucket S3 para armazenar o código do Lambda.

### Deploy com AWS CLI
1. Compacte o `.jar`:
   ```bash
   zip function.zip emergencia-familiar-1.0-SNAPSHOT.jar
   ```
2. Atualize o código da Lambda:
   ```bash
   aws lambda update-function-code \
     --function-name EmergencyFamily \
     --zip-file fileb://function.zip
   ```
   ```

---

## 🔍 **Logs e Monitoramento**
- Os erros são registrados no **CloudWatch** com detalhes das falhas no SNS ou na Lambda.
- Exemplo de log configurado no `EmergencyHandler`:
   ```java
   logger.error("Falha ao enviar mensagem: {}", e.getMessage());
   ```

---

## 🎯 **Contribuições**
Sinta-se à vontade para abrir **issues** ou enviar **pull requests** com melhorias ou correções.

---

## 📄 **Licença**
Este projeto está licenciado sob os termos da [MIT License](LICENSE).

---

### 🌟 **Por que este projeto?**
Este projeto combina as melhores práticas de integração de serviços AWS, desenvolvimento Java. Publicado como parte do meu portfólio público, visa inspirar outros desenvolvedores e demonstrar soluções para problemas do mundo real utilizando Alexa Skills.

