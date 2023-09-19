FROM openjdk:11
EXPOSE 5000
ADD target/userservice.jar userservice.jar
ENTRYPOINT ["java","-jar","/userservice.jar"]