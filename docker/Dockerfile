FROM --platform=linux/amd64 gradle:7-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle shadowJar --no-daemon

FROM openjdk:17
RUN mkdir /app
COPY --from=build /home/gradle/src/app/build/libs/*.jar /app/botlinera.jar
ENTRYPOINT java -jar /app/botlinera.jar

