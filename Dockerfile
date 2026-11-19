# --- Etapa 1: Build da aplicação com Maven e Java 21 ---
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copia os arquivos de configuração e o código fonte
COPY pom.xml .
COPY src ./src

# Executa o empacotamento pulando os testes para ser mais rápido na montagem
RUN mvn clean package -DskipTests

# --- Etapa 2: Execução leve apenas com o JRE 21 ---
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copia o arquivo .jar gerado na etapa anterior para dentro da imagem final
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]