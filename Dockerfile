FROM gradle:7-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM openjdk:11
RUN mkdir /app
WORKDIR /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/ktor-sample-all.jar
COPY --from=build /home/gradle/src/.env /app/.env
ENTRYPOINT ["java","-jar","/app/ktor-sample-all.jar"]
