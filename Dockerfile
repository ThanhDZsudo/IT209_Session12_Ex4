FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Non-root user for security best practice
RUN addgroup -S spring && adduser -S spring -G spring

# Copy pre-built JAR from Gradle build
COPY build/libs/*.jar app.jar

RUN chown spring:spring app.jar

USER spring:spring

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
