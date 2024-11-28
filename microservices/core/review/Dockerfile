FROM openjdk:17-jdk-alpine

COPY target/review-0.0.1-SNAPSHOT.jar review.jar

#EXPOSE 9000

ENTRYPOINT ["java", "-jar", "review.jar"]