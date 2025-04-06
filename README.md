# Merchant Management API

A Spring Boot application for managing merchants with JWT authentication and PostgreSQL database.

## Features

- Merchant Login
- Merchant Registration
- JWT Authentication
- Data Masking (Sensitive Data)
- PostgreSQL Database Integration
- Complete CRUD Operations
- Swagger API Documentation

## Prerequisites

- Java JDK 17
- Maven
- PostgreSQL

## Setup

1. Clone the repository:
```bash
git clone <repository-url>
cd merchant-api
```

2. Create PostgreSQL database:
```sql
CREATE DATABASE merchant_db;
```

3. Update database configuration in `src/main/resources/application.properties` if needed:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/merchant_db
spring.datasource.username=postgres
spring.datasource.password=postgres
```

4. Build the project:
```bash
mvn clean install
```

5. Run the application:
```bash
mvn spring-boot:run
```

## API Documentation

Once the application is running, you can access:
- Swagger UI: http://localhost:8080/swagger-ui.html
- API Docs: http://localhost:8080/api-docs

## API Endpoints

### Authentication
- POST `/api/v1/auth/register` - Register new merchant
- POST `/api/v1/auth/login` - Login merchant

### Merchant Operations (Requires JWT)
- GET `/api/v1/merchants` - Get all merchants
- GET `/api/v1/merchants/{id}` - Get merchant by ID
- POST `/api/v1/merchants` - Create new merchant
- PUT `/api/v1/merchants/{id}` - Update merchant
- DELETE `/api/v1/merchants/{id}` - Delete merchant

## Security

- JWT based authentication
- Password encryption using BCrypt
- Protected endpoints
- Input validation

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details 