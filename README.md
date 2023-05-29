# Productivity Dashboard App
Full-Stack Productivity Dashboard Application built from scratch front-end to back-end.

## CRUD Application - MVC Pattern
- FrontEnd: React w/ Typescript + Redux (State Management Library), BootStrap/ReactBS, WebPack (JS Bundler)
- Backend: Spring Framework - Spring Boot (Java, Spring Initializer)
- Database: PostgreSQL w/ PGAdmin4
- Data Access: Spring JDBC
- Testing: JUnit, Mockito
- Build Tool: Maven
- Verison Control: Git, Github
  - Workflow: Feature Branch Workflow (continous delivery & integration) 
- Dockerize Application: Docker Container for the Backend + Frontend & Database Server, write DockerFile to create image - after intial setup
- API: REST & Fetch
- Project Management Tool: GitHub Projects
- Wireframes: Figma

### Mission Statement / Vision statement
- One stop shop to track productivity, organize your day, and track personal progress

# Start Up

This is a Dockerized version of the Productivity Dashboard application.

## Prerequisites

Before running the application, you must have the following installed on your system:

- Docker

## Getting Started

1. Clone the repository to your local machine (HTTPS):

`git clone https://github.com/devaustin10/productivity-dashboard-app.git`

2. Change to the project directory:

`cd productivity-dashboard`

3. Start the application:

`docker-compose up`

This will start the PostgreSQL database, pgAdmin, and the frontend application in separate containers.

4. Access the application:

- To access pgAdmin, open a web browser and go to http://localhost:5050. Log in with the username and password specified in the `docker-compose.yml` file.
- Open Client Folder in VSCode and `npm install` to install the required dependencies.
- `npm start` then to access the frontend application, open a web browser and go to http://localhost:3000.

## Customization

You can customize the application by modifying the `docker-compose.yml` file. The following environment variables can be changed:

- `POSTGRES_USER`: The username for the PostgreSQL database.
- `POSTGRES_PASSWORD`: The password for the PostgreSQL database.
- `POSTGRES_DB`: The name of the database to be created in PostgreSQL.
- `PGADMIN_DEFAULT_EMAIL`: The email address to use for the pgAdmin user.
- `PGADMIN_DEFAULT_PASSWORD`: The password to use for the pgAdmin user.

You can also customize the frontend application by modifying the files in the `client` directory.

## Troubleshooting

- If you encounter any issues with the application, please refer to the logs for the individual containers by running the following command: `docker-compose logs <container-name>`
- Replace <container-name> with the name of the container that you want to view the logs for (e.g. postgres, pgadmin, or frontend).
