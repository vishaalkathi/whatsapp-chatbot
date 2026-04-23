# 🚀 WhatsApp Chatbot Backend (Spring Boot)

A simple backend simulation of a WhatsApp chatbot built using **Java + Spring Boot**.
This project exposes a REST API that receives messages and responds with predefined replies.

---

## 📌 Features

* REST API endpoint: `/webhook`
* Accepts JSON input simulating WhatsApp messages
* Returns predefined responses:

  * `Hi` → `Hello`
  * `Bye` → `Goodbye`
* Logs all incoming messages
* Deployed on Railway (publicly accessible)

---

## 🛠️ Tech Stack

* Java
* Spring Boot
* Maven
* REST APIs

---

## 📡 API Endpoint

### 🔗 Base URL

```
https://your-app-name.up.railway.app
```

### 📥 POST `/webhook`

#### Request Body:

```json
{
  "message": "Hi"
}
```

#### Response:

```json
{
  "reply": "Hello"
}
```

---

## 🧪 Testing the API

You can test the API using tools like **Postman** or curl.

### Example using curl:

```bash
curl -X POST https://your-app-name.up.railway.app/webhook \
-H "Content-Type: application/json" \
-d '{"message":"Hi"}'
```

---

## 🧾 Sample Responses

| Input Message | Response                  |
| ------------- | ------------------------- |
| Hi            | Hello                     |
| Bye           | Goodbye                   |
| Other         | Sorry, I don't understand |

---

## 📂 Project Structure

```
src/main/java/com/example/chatbot
│
├── controller/
│   └── WebhookController.java
│
├── service/
│   └── ChatService.java
│
├── model/
│   ├── MessageRequest.java
│   └── MessageResponse.java
```

---

## ▶️ Running Locally

1. Clone the repository:

```bash
git clone https://github.com/your-username/whatsapp-chatbot.git
cd whatsapp-chatbot
```

2. Run the application:

```bash
mvn spring-boot:run
```

3. API will be available at:

```
http://localhost:8080/webhook
```

---

## 🌍 Deployment

This project is deployed on Railway.

---

## 🎥 Demo

(Attach your demo video link here)

---

## 👨‍💻 Author

**Vishaal Kathi**

---