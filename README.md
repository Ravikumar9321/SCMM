# 📦 SCMM – Supply Chain Management Module

A production‑ready full‑stack application for managing **Suppliers**, **Products**, **Orders**, and **Customers**, built with **Spring Boot + React.js**, featuring secure JWT authentication, Swagger API documentation, and PostgreSQL database integration.

---

## 🚀 Tech Stack

### Backend
- Java 21  
- Spring Boot 3.5.5  
- Spring Security (JWT Authentication)  
- Spring Data JPA / Hibernate  
- PostgreSQL  
- Swagger/OpenAPI 3.0  
- Maven  

### Frontend
- React.js  
- Axios (with interceptors for JWT authentication)  
- React Router  
- useState & useEffect  
- Responsive CSS  

### Tools
- Postman (API Testing)  
- JUnit 5 & Mockito (Unit Testing)  
- Git & GitHub  
- Eclipse IDE  

---

## 🏗️ Architecture
**Frontend (React)** ➝ **REST API (Spring Boot)** ➝ **PostgreSQL Database**

Backend follows a clean layered architecture:
- **Controller Layer** – Handles HTTP requests  
- **Service Layer** – Business logic  
- **Repository Layer** – Database interaction  

---

## ✨ Features
- 👥 **Supplier Management** – Add, update, and track suppliers  
- 📦 **Product Management** – Inventory tracking with CRUD APIs  
- 📋 **Order Management** – Create and monitor orders  
- 🛒 **Customer Management** – Manage customer profiles  
- 🔒 **Secure Authentication** – JWT login & protected routes  
- 📘 **API Documentation** – Swagger UI with JWT integration  
- 🧪 **Testing** – Unit tests with JUnit 5 & Mockito  

---

## 🗄️ Database Design
**Entities:**
- **Supplier** → supplierId, name, contact, address  
- **Product** → productId, name, price, stock, supplier (Many‑to‑One)  
- **Order** → orderId, orderDate, totalAmount, customer (Many‑to‑One), products (Many‑to‑Many)  
- **Customer** → customerId, name, email, phone, orders (One‑to‑Many)  

---

## 📁 Project Structure
| Path | Description |
|------|-------------|
| `backend/` | Spring Boot API |
| `entity/` | Supplier, Product, Order, Customer entities |
| `controller/` | REST Controllers |
| `service/` | Business Logic |
| `repository/` | JPA Repositories |
| `frontend/` | React Application |
| `management/` | Dashboard, Suppliers, Products, Orders, Customers |
| `doc/` | Screenshots & Documentation |
| `README.md` | This file |

---

## 🔗 REST API Endpoints

### Supplier APIs
| Method | Endpoint       | Description        |
| ------ | -------------- | ------------------ |
| GET    | `/supplier`    | Get all suppliers  |
| POST   | `/supplier`    | Create supplier    |

### Product APIs
| Method | Endpoint       | Description        |
| ------ | -------------- | ------------------ |
| GET    | `/products`    | Get all products   |
| POST   | `/products`    | Add new product    |

### Order APIs
| Method | Endpoint       | Description        |
| ------ | -------------- | ------------------ |
| GET    | `/orders`      | Get all orders     |
| POST   | `/orders`      | Create new order   |

### Customer APIs
| Method | Endpoint       | Description        |
| ------ | -------------- | ------------------ |
| GET    | `/customers`   | Get all customers  |
| POST   | `/customers`   | Add new customer   |

---

## 🧪 How to Run Locally

### 1️⃣ Configure PostgreSQL
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/scmm
spring.datasource.username=postgres
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
server.port=8080


 ###  2️⃣ Backend Setup (Eclipse)

-->   Import the backend project into Eclipse IDE
-->   Right‑click the project → Run As → Spring Boot App
-->    Backend runs on: http://localhost:8080

###  3️⃣ Frontend Setup
-->    cd frontend
-->   npm install
-->   npm start