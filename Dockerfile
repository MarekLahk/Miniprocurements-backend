FROM openjdk:11.0.4
ADD /build/libs/procurementSystemBackend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
