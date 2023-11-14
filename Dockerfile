# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17 AS builder

# Set the working directory
WORKDIR /app

# Copy the POM file
COPY pom.xml .

# Copy the source code
COPY src src

# Build the application
RUN mvn clean package

# Stage 2: Create the final image
FROM openjdk:17-jre-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/FLotte-1.0-SNAPSHOT.jar /app/FLotte-1.0-SNAPSHOT.jar

# Expose port 8080
EXPOSE 8080

# Start the application
CMD ["java", "-jar", "FLotte-1.0-SNAPSHOT.jar"]

# docker build -t flotte-image .
# docker run -p 8080:8080 --name flotte-container flotte-image
