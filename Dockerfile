FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY . .
mvn clean test
