FROM gradle:8.12.0-jdk21-alpine AS builder
WORKDIR /app
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle/
RUN ./gradlew dependencies --no-daemon
COPY . /app/
RUN ./gradlew bootJar --no-daemon

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
