FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY build/libs/backend-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-jar", "/app/backend-0.0.1-SNAPSHOT.jar"]