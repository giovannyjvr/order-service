FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/order-service-1.0.0.jar ./order-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "order-service.jar"]
