FROM gradle:7-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle jar --no-daemon

FROM openjdk:11
RUN mkdir /app
COPY --from=build /home/gradle/src/app/build/libs/*.jar /app/botlinera.jar
ENTRYPOINT ["java","-jar","/app/botlinera.jar"]

