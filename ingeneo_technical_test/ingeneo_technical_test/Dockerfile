# Use a base image with JDK 11 and Maven
FROM maven:3.8.4-openjdk-11 AS build

# Copy files from your project
COPY . .

# Build the project using Maven
RUN mvn clean package -DskipTests

# Create a new image based on OpenJDK 11
FROM openjdk:11-jre-slim-buster


# Copy the built JAR file from the previous stage
COPY --from=build target/ingeneo_technical_test-0.0.1-SNAPSHOT.jar ingeneo_technical_test.jar

# Expose the port that the application will use
EXPOSE 8080

# Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "ingeneo_technical_test.jar"]