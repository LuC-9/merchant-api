# Merchant Management API

A Spring Boot application for managing merchants with JWT authentication and PostgreSQL database.

## Features

- Merchant Authentication (Login/Register)
- JWT Token Based Security
- Complete Merchant CRUD Operations
- Data Validation
- PostgreSQL Database Integration
- Swagger API Documentation
- Password Encryption

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
- API Docs: http://localhost:8080/v3/api-docs

## Authentication

### Register New Merchant
```http
POST /api/v1/auth/register
```
Request body:
```json
{
  "businessName": "Example Business",
  "email": "example@business.com",
  "password": "securepassword",
  "phoneNumber": "1234567890",
  "address": "123 Business St",
  "businessType": "Retail",
  "registrationNumber": "REG123",
  "taxId": "TAX123"
}
```
Response: JWT token

### Login
```http
POST /api/v1/auth/login
```
Request body:
```json
{
  "email": "example@business.com",
  "password": "securepassword"
}
```
Response: JWT token

## Protected Endpoints

All the following endpoints require JWT authentication. Add the JWT token to the Authorization header:
```
Authorization: Bearer your_jwt_token_here
```

### Get All Merchants
```http
GET /api/v1/merchants
```
Returns a list of all merchants.

### Get Merchant by ID
```http
GET /api/v1/merchants/{id}
```
Returns details of a specific merchant.

### Create New Merchant
```http
POST /api/v1/merchants
```
Request body:
```json
{
  "businessName": "New Business",
  "email": "new@business.com",
  "password": "securepassword",
  "phoneNumber": "1234567890",
  "address": "456 Business Ave",
  "businessType": "Service",
  "registrationNumber": "REG456",
  "taxId": "TAX456"
}
```

### Update Merchant
```http
PUT /api/v1/merchants/{id}
```
Request body: Same as create merchant

### Delete Merchant
```http
DELETE /api/v1/merchants/{id}
```
Deletes a merchant by ID.

## Security Features

- JWT based authentication
- Password encryption using BCrypt
- Input validation
- Protected endpoints
- Stateless session management

## Using Swagger UI

1. Open Swagger UI at http://localhost:8080/swagger-ui.html
2. Register a new merchant using `/api/v1/auth/register`
3. Login using `/api/v1/auth/login` to get JWT token
4. Click the "Authorize" button at the top
5. Enter your JWT token as: `Bearer your_token_here`
6. Now you can test all protected endpoints

## Error Handling

The API includes comprehensive error handling for:
- Invalid input validation
- Duplicate email addresses
- Entity not found
- Authentication failures
- Authorization failures

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details 