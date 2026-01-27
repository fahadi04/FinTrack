FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/fintrack-0.0.1-SNAPSHOT.jar fintrack-v1.0.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","fintrak-v1.0.jar"]