FROM openjdk:24-jdk-slim

ENV APP_PATH=/usr/src/app
RUN mkdir -p $APP_PATH
WORKDIR $APP_PATH

ARG SPRING_PROFILE=docker
ENV SPRING_PROFILE=${SPRING_PROFILE}

COPY target/windCatchers-0.0.1-SNAPSHOT.jar $APP_PATH/windCatchers.jar

ENTRYPOINT java -Dspring.profiles.active=$SPRING_PROFILE -jar  ${APP_PATH}/windCatchers.jar
