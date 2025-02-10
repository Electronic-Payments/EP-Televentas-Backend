# Usa una imagen base de Java (al elegir la versión de OpenJDK adecuada para tu aplicación)
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR de la aplicación al contenedor
COPY target/ep-televentas-backend-0.0.1-SNAPSHOT.jar /app/ep-televentas-backend.jar

# Establece las variables de entorno para Spring
ENV SPRING_PROFILES_ACTIVE=development
ENV SPRING_APPLICATION_NAME=ep-televentas-backend

# Expone el puerto para la aplicación
EXPOSE 9091

# Comando para ejecutar la aplicación con el perfil de producción
ENTRYPOINT ["java", "-jar", "ep-televentas-backend.jar"]
