# Verwenden Sie ein Java-Base-Image
FROM openjdk:11

# Installieren Sie Maven
RUN apt-get update && \
    apt-get install -y maven

# Legen Sie das Arbeitsverzeichnis im Container fest
WORKDIR /usr/src/app

# Kopieren Sie Ihre Java-Datei in das Docker-Image
COPY Consumer.java .

# Fügen Sie die Maven-Abhängigkeitsdatei 'pom.xml' hinzu
COPY pom.xml .

# Installieren Sie die Abhängigkeiten mit Maven
RUN mvn clean install


# Definieren Sie den Befehl zum Ausführen Ihrer Anwendung
CMD ["java", "-jar", "target/consumer-1.0.jar"]
