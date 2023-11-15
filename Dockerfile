FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
COPY my-config /home/app
RUN mvn -f /home/app/pom.xml clean package spring-boot:repackage

FROM openjdk:11-jre-slim
ARG PORT
ENV PORT=${PORT}
COPY --from=build /home/app/target/FLotte-1.0-SNAPSHOT.jar /usr/local/lib/demo.jar
ENTRYPOINT ["java","-Dserver.port=${PORT}","-jar","/usr/local/lib/demo.jar"]