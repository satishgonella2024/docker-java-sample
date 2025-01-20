FROM gradle:7.6-jdk11 AS builder
WORKDIR /app
# Copy the project files
COPY --chown=gradle:gradle . .
ENV JAVA_HOME=/opt/java/openjdk
RUN gradle build --no-daemon --warning-mode=all

FROM openjdk:11-jre-slim
WORKDIR /app
# Install netcat
RUN apt-get update && apt-get install -y netcat && rm -rf /var/lib/apt/lists/*
# Copy the wait-for-it script
COPY wait-for-it.sh /app/wait-for-it.sh
RUN chmod +x /app/wait-for-it.sh
# Copy the built JAR file from the builder stage
COPY --from=builder /app/app/build/libs/app.jar app.jar
ENTRYPOINT ["./wait-for-it.sh", "postgres", "5432", "--", "java", "-jar", "app.jar"]