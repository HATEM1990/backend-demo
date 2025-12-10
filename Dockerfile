# ============================
# Stage 1: Build the JAR
# ============================
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copy Maven wrapper and project files
COPY . .

# FIX: make mvnw executable on Linux
RUN chmod +x mvnw

# Build the application
RUN ./mvnw clean package -DskipTests

# ============================
# Stage 2: Run the JAR
# ============================
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy compiled JAR from build stage
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]