FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
#COPY sec.properties sec.properties
ENTRYPOINT ["java","-jar","/app.jar", "--spring.config.location=classpath:/application.properties"]
