# Animal Shelter Backend

A simple Spring Boot project intended as the backend for an animal shelter application. It currently serves as a foundation/demo of a Spring Boot service (Java 17) and can be run, tested, and packaged via Maven.

## Purpose

Provide a lightweight REST backend for an animal shelter system. It is built on Spring Boot to demonstrate or bootstrap further functionality (e.g., managing animals, adoptions, shelter data).

## Technologies & Languages

- **Java 17** (the language the application is written in)
- **Spring Boot 3.3.x** (application framework)
- **Maven** (build and dependency management)
- **JUnit / Spring Boot Test** (for unit/integration testing via `spring-boot-starter-test`)

## Prerequisites

- Java 17 (SDK installed and `java`/`javac` on PATH)
- Maven (or use the included Maven wrapper `./mvnw` / `mvnw.cmd`)
- Git (to clone the repository)

## Getting Started

Clone the repository and navigate to the backend module:

```bash
git clone <repository-url>
cd animal-shelter-main/animalshelter
```

### Development server

To run the application in development mode:

```bash
# Unix/macOS
./mvnw spring-boot:run
# Windows
mvnw.cmd spring-boot:run
```

Or with a system-installed Maven:

```bash
mvn spring-boot:run
```

The service will start on the default port `8080` (http://localhost:8080/) unless overridden via configuration.

### Building the JAR

To build a packaged JAR for production:

```bash
# Using wrapper
./mvnw clean package
# Or with installed Maven
mvn clean package
```

The resulting executable JAR will be placed in `target/` (e.g., `animalshelter-0.0.1-SNAPSHOT.jar`). Run it with:

```bash
java -jar target/animalshelter-0.0.1-SNAPSHOT.jar
```

### Running tests

Execute the test suite (unit/integration) via Maven:

```bash
# Using wrapper
./mvnw test
# Or with installed Maven
mvn test
```

## Project Structure (relevant parts)

```
animalshelter/                # Spring Boot backend module
  ├── mvnw, mvnw.cmd          # Maven wrapper scripts
  ├── pom.xml                 # Maven project definition
  └── src/                    # Source code
      └── main/java/...       # Application entrypoint and logic
```

## Environment / Configuration

Spring Boot uses `application.properties` or `application.yml`; environment-specific overrides can be provided via standard Spring mechanisms or command-line arguments, for example:

```bash
java -jar target/animalshelter-0.0.1-SNAPSHOT.jar --server.port=9090
```

## Common Commands

```bash
./mvnw spring-boot:run       # Start dev server
./mvnw clean package        # Build production artifact
./mvnw test                 # Run tests
```
