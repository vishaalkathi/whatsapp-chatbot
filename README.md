# 🤖 WhatsApp Chatbot Backend Simulation

A Spring Boot REST API that simulates a WhatsApp chatbot backend — receives messages via a `/webhook` endpoint and responds with predefined replies.

---

## 📁 Project Structure

```
whatsapp-chatbot/
├── src/
│   ├── main/java/com/chatbot/whatsapp/
│   │   ├── WhatsAppChatbotApplication.java   ← Entry point
│   │   ├── controller/
│   │   │   └── WebhookController.java        ← REST endpoints
│   │   ├── service/
│   │   │   └── ChatbotService.java           ← Reply logic + logging
│   │   ├── model/
│   │   │   ├── IncomingMessage.java          ← Request model
│   │   │   ├── ChatbotResponse.java          ← Response model
│   │   │   └── MessageLog.java               ← Log entry model
│   │   └── config/
│   │       └── GlobalExceptionHandler.java   ← Error handling
│   └── resources/
│       └── application.properties
├── pom.xml
├── render.yaml                               ← Render deploy config
└── README.md
```

---

## 🚀 Running Locally

### Prerequisites
- Java 17+
- Maven 3.6+

### Steps

```bash
# 1. Clone the repo
git clone https://github.com/YOUR_USERNAME/whatsapp-chatbot.git
cd whatsapp-chatbot

# 2. Build the project
mvn clean package -DskipTests

# 3. Run the app
mvn spring-boot:run
```

The server starts at: **http://localhost:8080**

---

## 📡 API Endpoints

### 1. POST `/webhook` — Receive a message

**Request:**
```json
{
  "from": "+911234567890",
  "message": "Hi",
  "timestamp": "2024-01-15T10:30:00"
}
```

**Response:**
```json
{
  "to": "+911234567890",
  "reply": "Hello! 👋 How can I help you today?",
  "status": "sent",
  "processedAt": "2024-01-15 10:30:01"
}
```

---

### 2. GET `/webhook/health` — Health check

```json
{
  "status": "UP ✅",
  "service": "WhatsApp Chatbot Backend",
  "version": "1.0.0",
  "totalMessages": 5
}
```

---

### 3. GET `/webhook/logs` — View all conversations

```json
{
  "totalMessages": 2,
  "conversations": [
    {
      "id": 2,
      "from": "+911234567890",
      "receivedMessage": "Bye",
      "botReply": "Goodbye! 👋 Have a wonderful day!",
      "loggedAt": "2024-01-15 10:31:00"
    }
  ]
}
```

---

## 💬 Supported Messages & Replies

| Input        | Bot Reply                                        |
|--------------|--------------------------------------------------|
| `Hi`         | Hello! 👋 How can I help you today?             |
| `Hello`      | Hey there! 😊 What can I do for you?            |
| `Bye`        | Goodbye! 👋 Have a wonderful day!               |
| `Goodbye`    | Take care! See you soon. 😊                     |
| `Help`       | Sure! You can say: Hi, Hello, Bye, Thanks...    |
| `Thanks`     | You're welcome! 🙏                               |
| `Thank you`  | Happy to help! 😊                               |
| `Hours`      | We're open Mon–Sat, 9 AM to 6 PM IST.          |
| `Location`   | We're based in Gorakhpur, UP, India 📍          |
| *(anything)* | I didn't quite understand that. 🤔              |

> Matching is **case-insensitive** — `HI`, `hi`, `Hi` all work.

---

## 🧪 Testing with cURL

```bash
# Send "Hi"
curl -X POST http://localhost:8080/webhook \
  -H "Content-Type: application/json" \
  -d '{"from": "+911234567890", "message": "Hi"}'

# Send "Bye"
curl -X POST http://localhost:8080/webhook \
  -H "Content-Type: application/json" \
  -d '{"from": "+919876543210", "message": "Bye"}'

# Health check
curl http://localhost:8080/webhook/health

# View logs
curl http://localhost:8080/webhook/logs
```

---

## 🧪 Running Tests

```bash
mvn test
```

Tests cover: Hi/Bye replies, case-insensitive matching, fallback responses, and message logging.

---

## ☁️ Deploying on Render (Bonus)

1. Push this repo to GitHub
2. Go to [render.com](https://render.com) → New → Web Service
3. Connect your GitHub repo
4. Render auto-detects `render.yaml` — click **Deploy**
5. Your live URL: `https://whatsapp-chatbot.onrender.com`

> ⚠️ Free tier on Render spins down after 15 minutes of inactivity. First request may be slow.

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3.2**
- **Maven**
- **SLF4J** for logging
- **JUnit 5** for tests

---
