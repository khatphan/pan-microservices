# Use an official JDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
#WORKDIR /app

# Copy the JAR file into the container
COPY target/discovery-server-0.0.1-SNAPSHOT.jar discovery-server.jar

# Expose port 8761
EXPOSE 8761

# Run the application
ENTRYPOINT ["java", "-jar", "discovery-server.jar"]