# Используем официальный образ Maven для сборки
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Копируем проект в контейнер
COPY . /app
WORKDIR /app

# Собираем приложение
RUN mvn clean package -DskipTests

# Используем официальный образ OpenJDK для запуска
FROM openjdk:21-jdk-slim

# Копируем собранный JAR-файл из стадии build
COPY --from=build /app/target/*.jar app.jar

# Открываем порт (например 8080)
EXPOSE 8080

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "app.jar"]