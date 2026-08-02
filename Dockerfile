FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY build/libs/*SNAPSHOT.jar SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "SNAPSHOT.jar"]