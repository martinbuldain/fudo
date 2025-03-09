# Construir la aplicacion
FROM eclipse-temurin:17-jdk as builder

WORKDIR /app

COPY . .

RUN ./gradlew build

# Ejecutarla
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=builder /app/build/libs/fudo-app.jar fudo-app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "fudo-app.jar"]