FROM openjdk:17-jdk-alpine

VOLUME /tmp

EXPOSE 9000

ARG JAR_FILE=target/pruebatecnica-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} pruebaTecnicaJMR.jar

ENTRYPOINT ["java","-jar","/pruebaTecnicaJMR.jar"]