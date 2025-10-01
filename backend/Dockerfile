# Build
FROM maven:3.9.11-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml and download dependencies first (for caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the backend code
COPY src ./src
RUN mvn clean install

# Runtime stage
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy only the packaged quarkus app
COPY --from=build /app/target/quarkus-app/ /app/

EXPOSE 8080
CMD ["java", "-jar", "quarkus-run.jar"]