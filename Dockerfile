FROM openjdk:13-alpine as builder
VOLUME /tmp
ARG JAR_FILE=target/springbootrestapi-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["/bin/bash", "-c", "java","-jar","/app.jar"]
