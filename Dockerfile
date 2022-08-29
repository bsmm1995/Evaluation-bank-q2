FROM openjdk:17-jdk-alpine
COPY ./target/practical.assesment.q2-0.0.1-SNAPSHOT.jar app-eb.jar
ENTRYPOINT ["java","-jar","/app-eb.jar"]