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
