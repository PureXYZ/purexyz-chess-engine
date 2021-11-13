# Build stage
FROM maven:3.6.3-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -Dmaven.test.failure.ignore=true -f /home/app/pom.xml clean install

# Package stage
FROM openjdk:17
COPY --from=build /home/app/target /usr/local/lib
ENTRYPOINT ["java","-jar","/usr/local/lib/maven-java-project-template-1.0-SNAPSHOT.jar"]
