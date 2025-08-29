# Bidding Platform

## Development

1. Start PostgreSQL using Docker Compose:
   ```bash
   docker-compose up -d
   ```
2. Run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```

The database will be preloaded with tables, indexes, and sequences from `db/init.sql`.

### Authentication

Register a user and obtain a JWT token:

```
POST /api/v1/auth/register
{
  "username": "user",
  "password": "pass"
}
```

Login to receive a JWT token:

```
POST /api/v1/auth/login
{
  "username": "user",
  "password": "pass"
}
```

Include the token in the `Authorization` header for all other requests:

```
Authorization: Bearer <token>
```
