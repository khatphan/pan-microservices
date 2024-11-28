FROM openjdk:17-jdk-alpine

COPY target/resource-server-0.0.1-SNAPSHOT.jar resource-server.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "resource-server.jar"]