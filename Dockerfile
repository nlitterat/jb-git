FROM openjdk:11 as builder
VOLUME /tmp
ARG JAR_FILE=target/springbootrestapi-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
CMD ["java","-jar", "/app.jar"]

