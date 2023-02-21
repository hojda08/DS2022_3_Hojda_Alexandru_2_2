FROM gradle:6.8.3-jdk11 AS build-project

COPY . /app

WORKDIR /app

RUN gradle clean build

FROM openjdk:14-jdk

EXPOSE 8070

COPY --from=build-project /app/build/libs/*.jar /app/app.jar

CMD ["java", "-jar", "/app/app.jar"]