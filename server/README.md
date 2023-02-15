# SERVER

### FEATURES:
- [ ] Open ID Connect (Google)
- [ ] Note-Taking
- [ ] Pomodoro Timer
- [ ] Planner
- [ ] Calendar (Figuring out implementing Google calendar)
- [ ] Music (Link YouTube Music)
- [ ] Email (Google)

### Stretch Goals / Future Development
- [ ] Productivity Tracking
  - Tracking how much time you're spending on certain things 
- [ ] Live Chat System
- [ ] Ability to create groups/teams/roles (users)

### BACKEND TECHNOLOGIES:
- IDE: Intellij
- Language: Java
- Frameworks: Spring (Spring Boot, Security, JDBC, Testing)
- Testing: JUnit, Mockito
- Database: PostgreSQL

### CONTROLLERS
- [ ] Http Request Endpoint Classes

### DATA
- Note-Taking
  - [ ] CREATE
  - [ ] READ
  - [ ] UPDATE
  - [ ] DELETE
  - [ ] Helper Methods
  - [ ] Mappers

### DOMAIN
- Note-Taking
  - [ ] CREATE
  - [ ] READ
  - [ ] UPDATE
  - [ ] DELETE
  - [ ] Helper Methods
  - [ ] Validations

### MODELS
- [ ] AppUser
  - int userId
    - Access token to connect to OpenId Connect
- [ ] Notes
  - int notesId
  - String body
  - timestamp
- [ ] PlannerEntry
  - int plannerEntryId
  - String body
  - timestamp

### SECURITY
- [ ] OpenID Connect
- [ ] AppUserService
- [ ] JwtConverter
- [ ] JwtRequestFilter
- [ ] SecurityConfig

### Q&A
1. OpenId Connect, where is the data being stored for user data?
  - User data is being stored in our own database.
  - OpenId Connect only verifies the user (says who they say they are).