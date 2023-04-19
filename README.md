# Productivity Dashboard App
Full-Stack Productivity Dashboard Application built from scratch front-end to back-end.

## CRUD Application - MVC Pattern
- FrontEnd: React w/ Typescript + Redux (State Management Library), BootStrap/ReactBS, WebPack (JS Bundler)
- Backend: Spring Framework - Spring Boot (Java, Spring Initializer)
- Database: PostgreSQL w/ PGAdmin4 (GUI)
- Data Access: Spring JDBC
- Testing: JUnit (Unit Testing), Mockito, Selenium (Integration Testing)
- Build Tool: Maven
- Verison Control: Git/Github
  - Workflow: Feature Branch Workflow (continous delivery & integration) 
- Dockerize Application: Docker Container (2 total) for the Backend + Frontend & Database Server, write DockerFile to create image - after intial setup
- API: REST & Fetch
- Project Management Tool: GitHub Projects
- Wireframe: Figma

### Mission Statement / Vision statement
- One stop shop to track productivity, organize your day, and track personal progress

### Generalized Challenges
- OpenId Connect for every feature

### What is the application?
- A dashboard app that has CRUD widgets - clock, claender, tasks, time per task...
- User accounts - with unique dashboard
- Teams feature with permissions (roles) granted to users
- Integrates google connet and also our own developed widgets (ex. pomodoro)

### What do you want in the dashboard?
- Planner aplication that tracks what you do -> simple no openid Connect, but has all the links for the widgets you use
- A mix of both-> the most used widgets would connect (calender: google calender),  genreal music(no options)
- Impliment one open id connect to create user profile but also provide linking to calender and other google services.

### Why is this application usefull?
- An everyday application to combine other applications into one
- It is customizable to users but also provides gnereal team use 

### Who would use the application?
- Incoming dev10 associates -> provide utility use
- Dev10 asociates -> provides purpose and personal connection -> it is motivating 

### What do you have open when you start your day? -> connect all of these into one dashboard
MH:  Spotify,  google, teams, outlook
LK:  Google calander, outlook,  big brain keeps it all up top,  Youtube music
LC:  Chat features (discord), calender, spotify, notes
AK:  Outlook, gmail, teams, sound cloud, pomodoro, many chrome tabs open, github, notes, reminders, calender - as todo list

### How do we make this applicaiton flexible??
- IDConnect - maybe

### Google Integration
- Gmail
- Calender

### Our Functionality
- Note pad
- Daily Journal 


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

- To access the frontend application, open a web browser and go to http://localhost:3000.
- To access pgAdmin, open a web browser and go to http://localhost:5050. Log in with the username and password specified in the `docker-compose.yml` file.

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
