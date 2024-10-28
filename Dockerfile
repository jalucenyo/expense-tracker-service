FROM openjdk:21-jdk

WORKDIR /app

COPY target/expense-tracker-service-*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
