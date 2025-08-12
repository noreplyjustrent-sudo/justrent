# Use OpenJDK 21 as base image
FROM openjdk:21-jdk
ADD target/justrent-0.0.1-SNAPSHOT.jar justrent-app.jar
ENTRYPOINT ["java", "-jar", "/justrent-app.jar"]
EXPOSE 8080