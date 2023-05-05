FROM openjdk:19
EXPOSE 8080
# first package app in a jar file then define the path to the jar file
# create jar file using command "mvn package"
ADD target/e-Banking-Portal-0.0.1-SNAPSHOT.jar e-Banking-Portal-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","e-Banking-Portal-0.0.1-SNAPSHOT.jar"]