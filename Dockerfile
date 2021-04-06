FROM openjdk:11.0.4
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENV spring.config.name=application-prod
ENTRYPOINT ["java","-jar","/app.jar"]
