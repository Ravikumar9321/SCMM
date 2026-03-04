# 📦 Supply Chain Management Module (SCMM)

Fullstack application for managing **Suppliers**, **Products**, **Orders**, and **Customers**.

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-green.svg)](https://spring.io/projects/spring-boot)
[![React](https://img.shields.io/badge/React-18-blue.svg)](https://reactjs.org)

## ✨ Features

- **Suppliers** 👥 - Manage supplier relationships
- **Products** 📦 - Track inventory levels  
- **Orders** 📋 - Process and monitor orders
- **Customers** 🛒 - Manage customer profiles
- **RESTful APIs** - Complete CRUD operations
- **Responsive UI** - Modern React dashboard

## 🏗️ Tech Stack

| Frontend | Backend | Database |
|----------|---------|----------|
| React 18 | Spring Boot 3.2 | PostgreSQL |
| React Router | Spring Data JPA | Hibernate |
| CSS Modules | Maven/Eclipse | |

## 🚀 Quick Start

### 1. Clone Repository
```bash
git clone https://github.com/Ravikumar9321/SCMM.git
cd SCMM

2. Backend Setup (Eclipse)
cd backend
# Open in Eclipse → Right-click project → Run As → Spring Boot App

Backend runs on: http://localhost:8080

3. Frontend Setup
cd frontend
npm install
npm start
Frontend runs on: http://localhost:3000

📁 Project Structure
SCMM/
├── backend/                 # Spring Boot API
│   ├── src/main/java/com/supplyManagement/
│   │   ├── Entity/  # Customer, Orders, Product, Supplier
│   │   ├── Dao/ # Data Access Objects
│   │   ├── Service/ #Business Logic
│   │   └── Repository/ # JPA Repositories
│   └── pom.xml
├── frontend/      # React App
│   ├── src/
│   │   ├── App.js
│   │   └── Home.jsx        
│   └── package.json
├── doc/   # Screenshort        
└── README.md

🌐 API Endpoints
| Method | Endpoint                        | Description       |
| ------ | ------------------------------- | ----------------- |
| GET    | http://localhost:8080/supplier | Get all suppliers |
| POST   | http://localhost:8080/supplier | Create supplier   |
| GET    | http://localhost:8080/products  | Get all products  |

🛠️ Environment Setup
Backend (Eclipse/Eclipse Run Configuration)
spring.datasource.url=jdbc:postgresql://localhost:5432/scmm
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
server.port=8080
