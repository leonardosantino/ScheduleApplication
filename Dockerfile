FROM ibm-semeru-runtimes:open-25-jdk AS builder

ENV AWS_REGION=us-east-1

WORKDIR /app

COPY . .

RUN ./gradlew build --no-daemon

FROM ibm-semeru-runtimes:open-25-jre

RUN groupadd --system jre && useradd --system --gid jre jre

USER jre

WORKDIR /app

COPY --chown=jre:jre --from=builder /app/build/libs/*SNAPSHOT.jar application.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "application.jar"]