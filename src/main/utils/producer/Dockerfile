# Verwenden Sie ein Java-Base-Image
FROM openjdk:11

# Legen Sie das Arbeitsverzeichnis im Container fest
WORKDIR /usr/src/app

# Kopieren Sie Ihre Java-Datei in das Docker-Image
COPY Producer.java .

# Kompilieren Sie Ihre Java-Datei
RUN javac Producer.java

# Definieren Sie den Befehl zum Ausführen Ihrer Anwendung
CMD ["java", "Producer.java"]