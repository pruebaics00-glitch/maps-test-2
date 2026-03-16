FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/backend-1.0-SNAPSHOT.jar /app/backend.jar
COPY config.yml /app/config.yml

EXPOSE 8000 8001

CMD ["java", "-jar", "backend.jar", "server", "config.yml"]
