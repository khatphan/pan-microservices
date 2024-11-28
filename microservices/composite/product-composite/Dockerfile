FROM openjdk:17-jdk-alpine

COPY target/product-composite-0.0.1-SNAPSHOT.jar product-composite.jar

#EXPOSE 9000

ENTRYPOINT ["java", "-jar", "product-composite.jar"]