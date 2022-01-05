FROM maven:3.8.1-openjdk-11 as build

COPY . /app

WORKDIR /app

RUN mvn clean package -DskipTests



FROM adoptopenjdk/openjdk11:jre-11.0.9_11.1-alpine

COPY --from=build /app/target/IasiBootcampBackend-0.0.1-SNAPSHOT.jar .

COPY --from=build /app/src/main/resources/application.yml .

EXPOSE 8080

ENTRYPOINT ["java","-jar","IasiBootcampBackend-0.0.1-SNAPSHOT.jar"]