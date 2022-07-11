FROM openjdk:11
COPY target/todolist-0.0.1-SNAPSHOT.jar todolist-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/todolist-0.0.1-SNAPSHOT.jar"]