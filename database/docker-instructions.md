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
#### When Running `localhost:8080`, Not Showing Our 0Auth Login Html
- SOLUTION: Need to recreate the `productivity-0.0.1-SNAPSHOT.jar` file in the `target` folder inside the `server` directory. We implemented 0Auth after packing up our application. Any major changes, we need to recreate the jar file.
  - `./mvnw clean package`

#### When Running `docker compose up`, Getting Error Saying That Port 5434 Is Already In Use
- SOLUTION: Need to see what is currently running on that port first and then kill it.
  - `sudo lsof -i :5432`
  - `sudo kill PID`

#### When Trying to Connect the `productivity_dashboard_test` on pgadmin, It Doesn't Connect
- When installing pgadmin on your local machine and exposing a port, you can connect to it through pgadmin without any major issues. 
- However, when installing pgadmin through Docker, it cannot communicate with your local machine by default. 
- To make this work, you need to specify "localhost" as the local container. 
- For example, if you set "productivity_dashboard_test" to use port 5432 (default port for PostgreSQL), you can connect to it through pgadmin running in the Docker container.
<br><br>
<image src="./docker-instructions-assets/docker-port-1.png" width="300">
<image src="./docker-instructions-assets/docker-port-2.png" width="300">
<br><br>
- SOLUTION: Remove all existing volumes from dockerhub (volumes vs. mount)

- USEFUL TERMINAL COMMANDS:
  - `docker ps` 
  - `docker logs container-name`
    - Brings up the logs inside terminal of each container.
  - `docker inspect container-name`
    - Inspects each container.
    - Scroll down to `NetworkSettings` and look at `Ports`. If you are inspecting the `productivity_dashboard_test`, you can see that it says port 5432 even though the host port is 5433.  
    
  ```aidl
            "Ports": {
                "5432/tcp": [
                    {
                        "HostIp": "0.0.0.0",
                        "HostPort": "5433"
                    }
                ]
            },
    ```
  - `docker exec -it container-name bash`
    - Transitions your local terminal command into container commands that are executed by the container.
    - Access a running Docker container and start a new Bash shell session within that container.
      - `docker exec`: This is the Docker command used to execute a command within a running container.
      -  `-it`: These are command options used to allocate a pseudo-TTY and enable interaction with the container's standard input (stdin).
      -  `container-name`: This is the name or ID of the container in which you want to start the Bash shell session.
      -  `bash`: This is the command to execute within the container, which in this case is the Bash shell.
  - `docker exec -it container-name psql -d database-name -U username`
    - Used to start a new shell session within a running Docker container.
    - Then run the PostgreSQL client psql to connect to a database called productivity_dashboard_test as a user called postgrestest.