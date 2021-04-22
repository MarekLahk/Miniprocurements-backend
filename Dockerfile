FROM openjdk:11.0.4
RUN addgroup --system --gid 1002 spring && adduser --system --uid 1002 --gid 1002 spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENV spring.config.name=application-prod
ENTRYPOINT ["java","-jar","/app.jar"]

