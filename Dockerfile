FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY build/libs/*.jar homework.jar
ENTRYPOINT ["java", "-jar", "homework.jar"]
