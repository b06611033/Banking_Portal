# E-Banking Portal
## Technologies
1. Spring
2. MySQL
3. Kafka
4. Swagger API
5. Docker
## Run locally
1. Install Maven
2. Create MySQL Database
3. Run with command

```
./mvnw spring-boot:run
```

## Run on Docker
```
docker build -t backend .
```

```
docker-compose up
```

## App Structure
### Controller
Provides api to create user and get transaction statements
### Service
Handles the logic between incoming request and response
### Repository
Provides data access operations
### Models
Defines Customer, Account, Transaction entity and communicates with database
### Filter
Provides authentication and authorization with JWT