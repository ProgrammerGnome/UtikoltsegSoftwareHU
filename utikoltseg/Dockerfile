FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/utikoltseg-0.0.1-SNAPSHOT.jar app.jar
COPY src/main/webapp /app/webapp
COPY src/main/resources/static/index.js /app/index.js
COPY src/main/resources/application.properties application.properties
COPY src/main/resources/utikoltseg.jrxml /app/utikoltseg.jrxml

EXPOSE 8080
CMD java -jar app.jar --spring.config.location=application.properties
