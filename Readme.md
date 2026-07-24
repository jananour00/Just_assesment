# E-Commerce Backend

A Spring Boot REST API backend for an e-commerce platform with JWT-based authentication, PostgreSQL database, and a layered architecture.

## Tech Stack

- **Java 21**
- **Spring Boot 4.1.0**
- **Maven**
- **PostgreSQL** (with JPA/Hibernate)
- **Spring Security** (JWT authentication)
- **Lombok**
- **Bean Validation (Jakarta Validation)**

## Prerequisites

- JDK 21
- Maven
- PostgreSQL

## Setup

1. **Clone the repository**

```bash
git clone <repository-url>
cd ecommerce-backend
```

2. **Configure the database**

Update `src/main/resources/application.yaml` with your PostgreSQL credentials:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/ecommerce_db
    username: postgres
    password: your_password
```

Make sure a database named `ecommerce_db` exists in PostgreSQL.

3. **Run the application**

```bash
./mvnw spring-boot:run
```

The server will start on `http://localhost:8080`.

## Authentication

This API uses **JWT (JSON Web Token)** authentication.

1. **Register** a new user via `POST /api/auth/register`
2. **Login** via `POST /api/auth/login` to receive a JWT token
3. Include the token in subsequent requests as:

```
Authorization: Bearer <your-jwt-token>
```

### Roles

- `USER` — default role for registered users
- `ADMIN` — administrative role

## API Endpoints

### Auth (`/api/auth`)

| Method | Endpoint      | Description       | Auth Required |
|--------|---------------|-------------------|:-------------:|
| POST   | `/register`   | Register new user | No            |
| POST   | `/login`      | Login and get JWT | No            |

### Users (`/api/users`)

| Method | Endpoint | Description           | Auth Required |
|--------|----------|-----------------------|:-------------:|
| GET    | `/`      | Get all users         | Yes           |
| GET    | `/{id}`  | Get user by ID        | Yes           |
| PUT    | `/{id}`  | Update user           | Yes           |
| DELETE | `/{id}`  | Delete user           | Yes           |

### Products (`/api/products`)

| Method | Endpoint   | Description              | Auth Required |
|--------|------------|--------------------------|:-------------:|
| GET    | `/`        | Get all products         | Yes           |
| GET    | `/{id}`    | Get product by ID        | Yes           |
| POST   | `/`        | Create new product       | Yes           |
| PUT    | `/{id}`    | Update product           | Yes           |
| DELETE | `/{id}`    | Delete product           | Yes           |

### Categories (`/api/categories`)

| Method | Endpoint   | Description              | Auth Required |
|--------|------------|--------------------------|:-------------:|
| GET    | `/`        | Get all categories       | Yes           |
| GET    | `/{id}`    | Get category by ID       | Yes           |
| POST   | `/`        | Create new category      | Yes           |
| PUT    | `/{id}`    | Update category          | Yes           |
| DELETE | `/{id}`    | Delete category          | Yes           |

### Cart (`/api/cart`)

| Method | Endpoint   | Description              | Auth Required |
|--------|------------|--------------------------|:-------------:|
| GET    | `/`        | Get user's cart          | Yes           |
| POST   | `/`        | Create cart              | Yes           |
| PUT    | `/{id}`    | Update cart              | Yes           |
| DELETE | `/{id}`    | Delete cart              | Yes           |

### Cart Items (`/api/cart-items`)

| Method | Endpoint   | Description              | Auth Required |
|--------|------------|--------------------------|:-------------:|
| GET    | `/`        | Get all cart items       | Yes           |
| GET    | `/{id}`    | Get cart item by ID      | Yes           |
| POST   | `/`        | Add item to cart         | Yes           |
| PUT    | `/{id}`    | Update cart item         | Yes           |
| DELETE | `/{id}`    | Remove item from cart    | Yes           |

### Orders (`/api/orders`)

| Method | Endpoint   | Description              | Auth Required |
|--------|------------|--------------------------|:-------------:|
| GET    | `/`        | Get all orders           | Yes           |
| GET    | `/{id}`    | Get order by ID          | Yes           |
| POST   | `/`        | Place new order          | Yes           |
| PUT    | `/{id}`    | Update order             | Yes           |
| DELETE | `/{id}`    | Cancel/delete order      | Yes           |

## Project Structure

```
src/main/java/ecommerce_backend/ecommerce_backend/
├── config/              # Security & application configuration
│   └── SecurityConfig.java
├── controller/          # REST API controllers
│   ├── AuthController.java
│   ├── CartController.java
│   ├── CartItemController.java
│   ├── CategoryController.java
│   ├── OrderController.java
│   ├── ProductController.java
│   └── UserController.java
├── dto/                 # Data Transfer Objects
│   ├── AuthDTO.java
│   ├── CartDTO.java
│   ├── CategoryDTO.java
│   ├── OrderDTO.java
│   ├── ProductDTO.java
│   └── UserDTO.java
├── exception/           # Custom exceptions & global handler
│   ├── BadRequestException.java
│   ├── EmailAlreadyExistsException.java
│   ├── GlobalExceptionHandler.java
│   ├── ResourceNotFoundException.java
│   └── UnauthorizedException.java
├── model/               # JPA entities
│   ├── BaseEntity.java
│   ├── Cart.java
│   ├── CartItem.java
│   ├── Category.java
│   ├── Order.java
│   ├── OrderItem.java
│   ├── Product.java
│   ├── Role.java
│   └── User.java
├── repository/          # Spring Data JPA repositories
├── security/            # JWT & security components
│   ├── CustomUserDetailsService.java
│   ├── JwtAuthenticationEntryPoint.java
│   ├── JwtAuthenticationFilter.java
│   └── JwtService.java
├── service/             # Business logic interfaces & implementations
│   ├── impl/            # Service implementations
│   └── ...
└── util/                # Utility classes
```

## Running Tests

```bash
./mvnw test
```

