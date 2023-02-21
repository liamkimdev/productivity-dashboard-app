# Uses this image as a base image
FROM openjdk:17-jdk-alpine
# Sets the working directory of the container to '/app'
WORKDIR /app
# copies the file from the target directory on the host machine to the '/app' directory in the container
COPY target/productivity-0.0.1-SNAPSHOT.jar /app
# exposes port
EXPOSE 8080
# specifies the command to run when the container is started
CMD ["java", "-jar", "productivity-0.0.1-SNAPSHOT.jar"]