# Use a base image with JDK 11 and Maven
FROM maven:3.8.4-openjdk-11 AS build

# Set a working directory
WORKDIR /app

# Copy files from your project into the working directory
COPY . /app

# Build the project using Maven
RUN mvn clean install

# Create a new image based on OpenJDK 11
FROM openjdk:11-jre-slim-buster

# Expose the port that the application will use
EXPOSE 8080

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/ingeneo_technical_test-0.0.1-SNAPSHOT.jar /app/ingeneo_technical_test.jar

# Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "/app/ingeneo_technical_test.jar"]
