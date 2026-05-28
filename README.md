# 🚀 DevOps Pipeline Tracker

A Full Stack DevOps Pipeline Tracking Application built using:

- React + Tailwind CSS
- Spring Boot
- PostgreSQL
- JWT Authentication
- Docker & Docker Compose

---

# 📌 Features

✅ JWT Authentication  
✅ CRUD Operations  
✅ Dockerized Architecture  
✅ PostgreSQL Database  
✅ REST APIs  
✅ Swagger Documentation  
✅ Responsive Dashboard UI  
✅ Pipeline Management

---

# 🛠️ Tech Stack

## Frontend
- React
- Vite
- Tailwind CSS
- Axios
- React Router DOM

## Backend
- Spring Boot
- Spring Security
- JWT
- Hibernate
- Spring Data JPA

## Database
- PostgreSQL

## DevOps
- Docker
- Docker Compose

---

# 📂 Project Structure

```bash
devops-tracker/
│
├── frontend/
│   ├── src/
│   ├── Dockerfile
│
├── src/
│
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

---

# 🐳 Docker Architecture

```text
React Frontend
       ↓
Spring Boot Backend
       ↓
PostgreSQL Database
```

All services run inside Docker containers.

---

# ⚙️ Run Application Using Docker

## Step 1️⃣ Install Docker

Download Docker Desktop:

https://www.docker.com/products/docker-desktop/

Start Docker Desktop before running the project.

---

## Step 2️⃣ Clone Repository

```bash
git clone <your-github-repository-url>
```

---

## Step 3️⃣ Navigate to Project

```bash
cd devops-tracker
```

---

## Step 4️⃣ Build Spring Boot JAR

```bash
mvn clean install -DskipTests
```

---

## Step 5️⃣ Start Application

```bash
docker compose up --build
```

This starts:

✅ PostgreSQL  
✅ Spring Boot Backend  
✅ React Frontend

---

# 🌐 Application URLs

## Frontend

```text
http://localhost:5173
```

---

## Backend API

```text
http://localhost:8080
```

---

## Swagger UI

```text
http://localhost:8080/swagger-ui/index.html
```

---

# 🛑 Stop Application

```bash
docker compose down
```

---

# 🔄 Restart Application

```bash
docker compose up
```

---

# 💻 Run Application Without Docker

# Backend

## Step 1️⃣ Navigate to Root Folder

```bash
cd devops-tracker
```

---

## Step 2️⃣ Run Spring Boot

```bash
mvn spring-boot:run
```

Backend runs on:

```text
http://localhost:8080
```

---

# Frontend

## Step 1️⃣ Navigate to Frontend

```bash
cd frontend
```

---

## Step 2️⃣ Install Dependencies

```bash
npm install
```

---

## Step 3️⃣ Run Frontend

```bash
npm run dev
```

Frontend runs on:

```text
http://localhost:5173
```

---

# 🔐 Authentication

This project uses JWT Authentication.

Demo Login:

```text
Username: admin
Password: admin123
```

---

# 📌 API Endpoints

## Authentication

| Method | Endpoint |
|---|---|
| POST | `/auth/login` |

---

## Pipelines

| Method | Endpoint |
|---|---|
| GET | `/pipelines` |
| POST | `/pipelines` |
| PUT | `/pipelines/{id}` |
| DELETE | `/pipelines/{id}` |

---

# 📊 Features Implemented

- Pipeline Creation
- Pipeline Update
- Pipeline Deletion
- Dashboard Statistics
- JWT Login
- Protected APIs
- Real-time Data Fetching
- Dockerized Deployment

---

# 🚀 Future Enhancements

- CI/CD Integration
- Jenkins Integration
- Kubernetes Deployment
- Role-Based Access Control
- Deployment Tracking
- Charts & Analytics

---

# 👨‍💻 Author

Developed by Krupa Varma 🚀