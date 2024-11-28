FROM openjdk:17-jdk-alpine

COPY target/product-0.0.1-SNAPSHOT.jar product.jar

#EXPOSE 9000

ENTRYPOINT ["java", "-jar", "product.jar"]