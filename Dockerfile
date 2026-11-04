FROM eclipse-temurin:25-jre-alpine

# Création d'un groupe et d'un utilisateur non-root
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

WORKDIR /app

# Copie du .jar déjà construit par le workflow
COPY target/*.jar app.jar

# Configuration des permissions pour l'utilisateur non-root
RUN chown -R appuser:appgroup /app

# Basculer vers l'utilisateur non-root
USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
