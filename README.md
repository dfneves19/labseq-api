# Labseq Calculator

This project implements a REST service to calculate the **labseq sequence** (`l(n)`) using **Quarkus** for the backend and an **Angular** frontend. The solution also includes Docker setup for both backend and frontend.

---

## Labseq Sequence Definition

```
n = 0 => l(0) = 0 
n = 1 => l(1) = 1
n = 2 => l(2) = 0
n = 3 => l(3) = 1
n > 3 => l(n) = l(n-4) + l(n-3) 
```


---

## Features

- **Backend REST API** using Quarkus.
- **Caching** in the service for intermediate calculations.
- Fast computation: `l(100000)` returns in under 10 seconds.
- **Unit tests** with 100% coverage using JUnit 5 and JaCoCo.
- **OpenAPI / Swagger documentation** for the REST API.
- Optional **Angular frontend** to interact with the service.
- **Dockerized** for easy execution of both backend and frontend.

---
## Endpoint
#### GET /labseq/{n}

- `{n}`: non-negative integer
- Response (JSON):

```json
{
  "n": 20,
  "value": "129"
}
```

- Errors(HTTP 400):

```json
"n must be a positive integer"
```

---

## Run Locally

### Backend

- Navigate to the backend folder:
```
cd backend
```
- Build and run in dev mode:
```
mvn clean install
mvn quarkus:dev
```
- API available at:
``
http://localhost:8080/labseq/{n}
``

### Frontend

- Navigate to the frontend folder:
```
cd frontend
npm install
ng serve
```
- App available at:
``http://localhost:4200``
---
## Run with Docker

From the main folder run:
```
docker-compose up --build
```

---
## API Documentation (Swagger/OpenAPI) 

Quarkus OpenAPI describes `/labseq/{n}` and it is available at:

```http://localhost:8080/q/swagger-ui/```



---
## Testing 

### Backend Unit Tests:

To run all backend tests:
```
cd backend
mvn test
```

To generate coverage report using JaCoCo:
```
mvn jacoco:report
```
- Report available at: ``target/site/jacoco/index.html``
