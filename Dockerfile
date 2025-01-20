# Stage 1: Build the application
FROM gradle:7.6-jdk11 AS builder
WORKDIR /app
# Copy the project files
COPY --chown=gradle:gradle . .
# Set JAVA_HOME explicitly to use JDK 11
ENV JAVA_HOME=/opt/java/openjdk
# Build the application with Java 11
RUN gradle build --no-daemon --warning-mode=all

# Stage 2: Create a runtime image
FROM openjdk:11-jre-slim
WORKDIR /app
# Copy the built JAR file from the builder stage
COPY --from=builder /app/app/build/libs/app.jar app.jar
# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]