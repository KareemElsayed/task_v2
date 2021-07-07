FROM openjdk:8
ADD target/task_v1.jar task_v1.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "task_v1.jar"]

