# Use an official JDK runtime as a parent image
FROM openjdk:17-jdk-alpine
LABEL authors="ADMIN-PHAN"

# Set the working directory in the container
#WORKDIR /app

# Copy the JAR file into the container
COPY target/zipkin-server-0.0.1-SNAPSHOT.jar zipkin-server.jar

# Expose port 8761
EXPOSE 9411

# Run the application
ENTRYPOINT ["java", "-jar", "zipkin-server.jar"]