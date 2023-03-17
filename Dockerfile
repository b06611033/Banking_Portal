FROM eclipse-temurin:19-jdk-alpine 
LABEL maintainer="pohanhou@gmail.com"
ADD target/e-Banking-Portal-0.0.1-SNAPSHOT.jar e-Banking-Portal-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","e-Banking-Portal-0.0.1-SNAPSHOT.jar"]