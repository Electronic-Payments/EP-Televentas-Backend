# Usa una imagen de Maven para construir el artefacto
FROM maven:3.8.4-openjdk-17-slim AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo pom.xml y los archivos del proyecto
COPY pom.xml /app/
COPY src /app/src

# Construye el JAR con Maven
RUN mvn clean package -DskipTests

# Usa una imagen de OpenJDK para ejecutar el artefacto
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el JAR generado desde la imagen anterior
COPY --from=build /app/target/ep-televentas-backend-0.0.1-SNAPSHOT.jar /app/ep-televentas-backend.jar

# Establece las variables de entorno para Spring
ENV SPRING_PROFILES_ACTIVE=production
ENV SPRING_APPLICATION_NAME=ep-televentas-backend

# Expone el puerto para la aplicación
EXPOSE 9091

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "ep-televentas-backend.jar"]
