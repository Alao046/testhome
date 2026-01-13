# Use official Java 21 image
FROM eclipse-temurin:21-jdk-alpine

# Set working directory
WORKDIR /app

# Copy Maven wrapper and project files
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src ./src

# Make mvnw executable
RUN chmod +x mvnw

# Build jar inside Docker
RUN ./mvnw clean package -DskipTests

# Copy built jar to standard name
RUN cp target/*.jar app.jar

# Expose port
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java","-jar","app.jar"]
