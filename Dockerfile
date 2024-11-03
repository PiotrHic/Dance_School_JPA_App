FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/Dance_School_JPA_APP-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]