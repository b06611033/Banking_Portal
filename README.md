# E-Banking Portal
## Technologies
1. Spring
2. MySQL
3. Swagger API
4. Docker
## Run locally
1. Install Maven
2. Create MySQL Database
3. Run with command

```
./mvnw spring-boot:run
```

## Build and run on Docker
```
docker-compose up --build
```


## App Structure
### Controller
Provides api endpoint
### Service
Handles the logic between incoming request and response
### Repository
Provides data access operations
### Models
Defines User, Account, Transaction entities and communicates with database
