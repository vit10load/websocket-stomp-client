# 📡 WebSocket com Java e STOMP Client

Este repositório contém um exemplo prático de como configurar e utilizar **WebSockets** em uma aplicação **Spring Boot**, utilizando **STOMP (Simple Text Oriented Messaging Protocol)** para comunicação entre cliente e servidor.

---

## 📌 Índice

- [📖 Introdução](#-introdução)
- [🛠 Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [⚙️ Configuração do Servidor WebSocket](#️-configuração-do-servidor-websocket)
- [🚀 Criando o Cliente STOMP](#-criando-o-cliente-stomp)
- [🛠 Como Executar](#-como-executar)
- [📬 Testando com Postman e Browser](#-testando-com-postman-e-browser)
- [📜 Referências](#-referências)

---

## 📖 Introdução

Os **WebSockets** permitem comunicação bidirecional em tempo real entre o servidor e o cliente, sendo ideais para **aplicações de chat, notificações em tempo real e dashboards dinâmicos**.

O **STOMP (Simple Text Oriented Messaging Protocol)** é um protocolo de mensagens que roda sobre WebSockets, permitindo o uso de tópicos e filas de mensagens, semelhante a um broker de mensagens.

---

## 🛠 Tecnologias Utilizadas

- ☕ **Java 17+**
- 🏗 **Spring Boot 3.x**
- 📡 **Spring WebSocket**
- 📬 **STOMP (Simple Text Oriented Messaging Protocol)**
- 🚀 **SockJS (fallback para navegadores que não suportam WebSockets)**

---

## ⚙️ Configuração do Servidor WebSocket

### 1️⃣ **Adicionando dependências**

No arquivo `pom.xml` (Maven):

```xml
<dependencies>
    <!-- Spring WebSocket -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-websocket</artifactId>
    </dependency>

    <!-- Spring Messaging (STOMP) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-messaging</artifactId>
    </dependency>
</dependencies>
```
