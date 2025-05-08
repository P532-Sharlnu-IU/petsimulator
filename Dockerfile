FROM eclipse-temurin:17
WORKDIR /home
COPY ./target/petsimulator-0.0.1-SNAPSHOT.jar pet-service.jar
ENTRYPOINT ["java", "-jar", "pet-service.jar"]