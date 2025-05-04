# Stage 1: build
FROM maven:3.9.9-amazoncorretto-8-al2023 AS build
WORKDIR /app

# Copy the entire project (excluding target via .dockerignore if needed)
COPY info-flow-tracker . 

RUN mvn clean package -DskipTests

# Stage 2: runtime
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/info-flow-tracker-0.0.1-SNAPSHOT.jar app.jar
ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
