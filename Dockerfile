FROM openjdk:11

COPY target/*.jar api-superheroes.jar

ENTRYPOINT ["java","-jar","/api-superheroes.jar"]