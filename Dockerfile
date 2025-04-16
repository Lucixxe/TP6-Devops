# Étape 1 : build avec Maven
FROM maven:3.9.4-eclipse-temurin-17 AS builder

WORKDIR /app

# Copie du code source
COPY . .

# Compilation du projet + tests
RUN mvn clean package -DskipTests=false

# Étape 2 : image finale légère avec juste Java
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copier le jar
COPY --from=builder /app/target/dataframe-lib-1.0-SNAPSHOT.jar app.jar

# Copier le fichier CSV (et le dossier si besoin)
COPY data data

# Commande par défaut
CMD ["java", "-jar", "app.jar"]