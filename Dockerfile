FROM eclipse-temurin:17-jdk-jammy

# Install Maven
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY . .

# Run Maven test
RUN mvn clean test
