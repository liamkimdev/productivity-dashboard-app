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
- DashboardController
- NoteWidgetController
- NoteController

### DATA
- DashboardJdbcTemplateRepository
  - One user can have multiple dashboards
    - CREATE
      - [ ] public Dashboard addDashboard(Dashboard dashboard)
    - READ
      - [ ] public Dashboard findByDashboardName(String dashboardName)
    - UPDATE
      - [ ] public boolean updateDashboardById(int dashboardId)
        - Update dashboard name only. Do this last. Still open to changes.
    - DELETE
      - [ ] public boolean deleteDashboardById (int dashboardId)
      - Helper Methods
      - Mappers

- NoteWidgetJdbcTemplateRepository
  - Note Widget would have a search bar.
    - CREATE
      - [ ] public NoteWidget addNoteWidget(NoteWidget noteWidget)
    - DELETE
      - [ ] public boolean deleteByNoteWidgetId(int noteWidgetId)
      
- NoteJdbcTemplateRepository
  - Notes functions should all be under the `Note` class.
  - User clicks the "add" button to create a note. Title & description of note will be default null at creation of note (no need for submit button to "add" note to database).
  - Adding the information to the note will not be an update. 
    - CREATE
      - [ ] public Note addNote(Note note)
    - READ
      - [ ] public Note findByNoteDate(LocaleDate date)
      - [ ] public Note findByNoteDescription(String noteDescription)
        - Updating search results based on keystrokes.
        - reddit vs apple
      - [ ] public List<Note> findAll()
    - UPDATE
      - [ ] public boolean updateNoteById(int noteId)
    - DELETE
      - [ ] public boolean deleteNoteById (int noteId)
    - Helper Methods
    - Mappers

### DOMAIN
- DashboardService
  - CREATE
    - [ ] public Result<Dashboard> addDashboard(Dashboard dashboard)
  - READ
    - [ ] public Dashboard findByDashboardName(String dashboardName)
  - UPDATE
    - [ ] public Result<Dashboard> updateDashboardById(int dashboardId)
      - Update dashboard name only. Do this last. Still open to changes.
  - DELETE
    - [ ] public boolean deleteDashboardById (int dashboardId)
  - Helper Methods
  - Validations
    - [ ] private Result<Dashboard> validate(Dashboard dashboard)
      - Dashboard name cannot be null
      - Dashboard name should be unique to use

- NoteWidgetService
  - CREATE
    - [ ] public NoteWidget addNoteWidget(NoteWidget noteWidget)
  - DELETE
    - [ ] public boolean deleteByNoteWidgetId(int noteWidgetId
    - Validations
      - No validations as of now
    
- NoteService
  - CREATE
    - [ ] public Result<Note> addNote(Note note)
  - READ
    - [ ] public Note findByNoteDate(LocaleDate date)
    - [ ] public Note findByNoteDescription(String noteDescription)
    - [ ] public List<Note> findAll()
  - UPDATE
    - [ ] public Result<Note> updateNoteById(int noteId)
  - DELETE
    - [ ] public boolean deleteNoteById (int noteId)
  - Helper Methods
  - Validations
    - [ ] private Result<Note> validate(Note note)
      - Size of description (max word count)
      - Size of title (max word count)
      - noteId cannot be null 
      - Timestamp cannot be null
      
- Result
- ResultType

### MODELS
- [ ] Dashboard
- [ ] Notes
  - int notesId
  - String body
  - timestamp
- [ ] NotesWidget
- [ ] AppUser
  - int userId
    - Access token to connect to OpenId Connect
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