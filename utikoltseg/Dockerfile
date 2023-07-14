FROM openjdk:17-alpine as build
WORKDIR /app
COPY . /app
RUN chmod +x ./mvnw
RUN ./mvnw wrapper:wrapper
RUN ./mvnw clean install

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
