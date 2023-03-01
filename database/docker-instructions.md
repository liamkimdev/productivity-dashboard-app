# Docker Instructions

## BackEnd Container

### Setting Up the BackEnd Container

#### Before Starting
- If this is not the first time setting up the Docker container for this project on your local machine, make sure that you delete any existing containers on the DockerHub.
- Delete any existing `postgres-data` folder in the `database` folder. 

#### Getting Started: Setting Up the BackEnd Container
1. Make sure you are in the root level of the `server` directory.
   - `cd productivity-dashboard`
   - `cd server`
 
2. Build a Java application using the Maven build tool. This is to create a deployable package (JAR file) of the application.
   - `./mvnw clean package`
   - Make sure that you see a `productivity-0.0.1-SNAPSHOT.jar` file in the `target` folder inside the `server` directory.
     - `./mvnw`: Runs the Maven wrapper, which is a script that allows you to run Maven without having to install it on your machine.
     - `clean`: Removes any files generated from a previous build.
     - `package`: Complies the source code and packages it into an executable JAR file.
       - TIP: If you get an error saying `./mvnw clean package permission denied` then run the following command to grant executable permissions to the `mvnw` script: 
         - `chmod +x mvnw`

3. Create the image for the backend.
    - `docker build -t springboot-backend .`

4. Go to the root level of the project where the `docker-compose.yml` file is located.

5. Run `docker compose up`.
   - You should see on DockerHub that all images are running on one container.

### Creating the BackEnd Container
1. `Dockerfile` was created on the root level of the `server` directory.
   - This Dockerfile is used to build a Docker image for our application.
```aidl
# Uses this image as a base image
FROM openjdk:17-jdk-alpine
# Sets the working directory of the container to '/app'
WORKDIR /app
# Copies the file from the target directory on the host machine to the '/app' directory in the container
COPY /target/productivity-0.0.1-SNAPSHOT.jar /app
# Exposes port
EXPOSE 8080
# Specifies the command to run when the container is started
CMD ["java", "-jar", "productivity-0.0.1-SNAPSHOT.jar"]
```

2. `pom.xml` was updated to include the following.
   - This is needed to specify the main class that should be used when the Spring Boot application is started. 
```aidl
<build>
	<plugins>
		<plugin>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-maven-plugin</artifactId>
			<configuration>
				<mainClass>org.productivity.App</mainClass>
			</configuration>
		</plugin>
	</plugins>
</build>
```

3. Follow the steps above in `Getting Started: Setting Up the BackEnd Container`.


### Issues
## When running localhost:8080, Not Showing Our 0Auth Login Html
- SOLUTION: Need to recreate the `productivity-0.0.1-SNAPSHOT.jar` file in the `target` folder inside the `server` directory. We implemented 0Auth after packing up our application. Any major changes, we need to recreate the jar file.
  - `./mvnw clean package`

