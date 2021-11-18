FROM openjdk:13-alpine as builder
VOLUME /tmp
ARG JAR_FILE=iam-0.0.1-SNAPSHOT-exec.jar
COPY ${JAR_FILE} app.jar
RUN java -jar app.jar extract
