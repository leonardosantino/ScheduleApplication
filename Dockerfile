FROM eclipse-temurin:21-jre-alpine

RUN addgroup -S jre && adduser -S jre -G jre

USER jre

WORKDIR /app

COPY --chown=jre:jre build/libs/*SNAPSHOT.jar SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "SNAPSHOT.jar"]