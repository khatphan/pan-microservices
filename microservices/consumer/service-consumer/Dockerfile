FROM openjdk:17-jdk-alpine

COPY target/service-consumer-0.0.1-SNAPSHOT.jar service-consumer.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "service-consumer.jar"]