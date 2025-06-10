# Stage 1: build com Maven
FROM maven:3.8.8-jdk-17-slim AS builder

WORKDIR /app

# Copia o pom.xml e baixa todas as dependências
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o código-fonte e empacota o JAR (pulando os testes)
COPY src ./src
RUN mvn package -DskipTests

# Stage 2: runtime com apenas o JDK
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Expõe a porta na qual o serviço roda (definida em application.properties)
EXPOSE 8082

# Copia o JAR gerado no estágio de build
COPY --from=builder /app/target/*.jar app.jar

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]
