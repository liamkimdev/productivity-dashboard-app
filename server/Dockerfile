# Uses this image as a base image
FROM bellsoft/liberica-openjdk-alpine:17
# Sets the working directory of the container to '/app'
WORKDIR /app
# Copies the file from the target directory on the host machine to the '/app' directory in the container
COPY target/productivity-0.0.1-SNAPSHOT.jar /app
# Exposes port
EXPOSE 8080
# Specifies the command to run when the container is started
CMD ["java", "-jar", "productivity-0.0.1-SNAPSHOT.jar"]