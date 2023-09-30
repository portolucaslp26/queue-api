# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY . /app
RUN mvn clean install

# Stage 2: Package the application into a lightweight container
FROM openjdk:17-slim
COPY --from=build /app/target/queue-api.jar /app/queue-api.jar
EXPOSE 8000
CMD ["java", "-jar", "devel-0.0.1-SNAPSHOT.jar"]