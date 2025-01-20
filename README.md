# Dockerized Java Sample App

This repository demonstrates a simple Java application built with Gradle, Dockerized for ease of deployment. The app fetches data from a public API and queries a PostgreSQL database for demonstration purposes.

## Prerequisites

1. Install [Docker](https://docs.docker.com/get-docker/) and [Docker Compose](https://docs.docker.com/compose/install/).
2. Install [Gradle](https://gradle.org/install/) (if building locally).

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/satishgonella2024/docker-java-sample.git
cd docker-java-sample
```
2. Build the Docker Image
```bash
./gradlew clean build
docker build -t java-sample-app .
```
3. Run the Application with Docker Compose
```bash
docker-compose up -d --force-recreate
```
4. Verify PostgreSQL Initialization
```bash
docker logs postgres-demo
```
5. Check Application Logs
```bash
docker logs java-sample-app
```
6. Query the Database
```bash
psql -h localhost -p 5432 -U user -d sampledb -c "SELECT * FROM users;"
```

The default credentials and database details are set in docker-compose.yml:
	•	User: user
	•	Password: password
	•	Database: sampledb

Project Structure
```bash
.
├── Dockerfile                 # Docker configuration for multi-stage builds
├── docker-compose.yml         # Docker Compose configuration
├── init.sql                   # SQL script for database initialization
├── app                        # Java application source and build files
│   ├── src/main               # Application source code
│   ├── src/test               # Unit tests
│   └── build.gradle           # Gradle build configuration
└── README.md                  # Project documentation
```
How to Extend
	•	Modify SQL Data: Edit init.sql to change initial database entries.
	•	Add Dependencies: Update app/build.gradle to include additional libraries.
	•	Change API Endpoint: Update ApiService.java with a different API URL.

Known Issues
	•	Ensure no other services are running on port 5432 to avoid conflicts.
	•	Use Docker Compose’s --force-recreate flag to refresh containers if changes are not reflected.

Contributing

Feel free to fork the repository and submit pull requests.
