# Quora-like Backend Application

A **Quora-like backend application** built using **Reactive Spring WebFlux** with features such as questions, answers, comments, tags, user follow system, and efficient feed generation using Kafka and Redis.

---

## 🚀 Features

- Create, update, and manage **Questions**, **Answers**, **Comments**, and **Tags**.
- User **Signup** and **Follow** other users.
- **Feed Generation Service** powered by **Kafka** and **Redis**:
    - On signup, user receives a dummy custom feed.
    - After following others, feed updates with posts from followed users.
- **Full-Text Search** for questions and answers using **ElasticSearch**.
- Reactive, non-blocking architecture for scalable performance.

---

## ⚡ Tech Stack

- **Java**
- **Spring WebFlux (Reactive)**
- **Apache Kafka**
- **Redis**
- **ElasticSearch**
- **MongoDB**
- **Gradle**

---

## 🎯 Feed Generation Strategy

1. User signs up → receives a dummy custom feed.
2. User follows others → real-time feed built via Kafka event stream.
3. Feed data cached in Redis for fast retrieval.

---

## ✅ How to Run

### Prerequisites
- Java 17+
- Docker (for Redis, Kafka, ElasticSearch, MongoDB)
- Gradle


### Steps
1. Clone the repository:
    ```bash
    git clone https://github.com/ShivanChowdhury9/Quora.git
    cd your-repo
    ```

2. Now after you have the Project in local start by making changes for your own config for Kafka, Redis, Elastic Search and Mongo.

---

## 📚 API Endpoints

- `/user` – Manage users and follow/unfollow other users based on your choice
- `/questions` – CRUD for questions
- `/answers` – CRUD for answers
- `/comment` – CRUD for comments
- `/tags` – CRUD for tags
- `/feed` – Complete Feed generation service logic


---

## 🛠️ Future Scope

- Implement OAuth2 Authentication
- API Documentation (Swagger)

---

## 📄 License

This project is open-sourced under the MIT License.

---

⭐ Feel free to contribute or raise issues for improvements!
