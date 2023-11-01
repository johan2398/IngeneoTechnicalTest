# Use a base image with JDK 11 and Maven
FROM maven:3.8.4-openjdk-11 AS builder

COPY ./ingeneo_technical_test/ingeneo_technical_test/src src/
COPY ./ingeneo_technical_test/ingeneo_technical_test/pom.xml pom.xml

# Build the project using Maven
RUN mvn clean package -DskipTests

# Create a new image based on OpenJDK 11
FROM openjdk:11-jre-slim-buster

# Copy the built JAR file from the build stage
COPY --from=builder ./ingeneo_technical_test/ingeneo_technical_test/*.jar ingeneo_technical_test.jar

# Expose the port that the application will use
EXPOSE 8080

# Set the entry point to run the application
CMD ["java", "-jar", "ingeneo_technical_test.jar"]
