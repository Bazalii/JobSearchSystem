# CV backend

Web application backend that allows to create a resume using a template,
add and get commentaries about worker's experience and companies. Using 
this information HR of a company may find a wanted candidate for a position
in the company or a job-seeker may find a suitable job.

## Entity relationship diagram

![DataBase](/dataBase.JPG)

- User - user of the application
- Resume - information for main page of resume
- Project - project of a user
- WorkExperienceItem - information about a user's work during a certain period of time
- Commentary - commentary of a user
- ProgrammingLanguage - programming language that user can add to their resume
- Framework - framework that user can add to their resume
- Database - database that user can add to their resume

Only admins are allowed to change tables in the database that contain programming
languages, frameworks and databases. Users can only get fixed list of elements when they
are filling resume form.

## How to start the application

### _Gradle(in dev mode):_

`./gradlew web:quarkusDev`

### _Docker:_

`docker-compose -f docker/docker-compose.yaml up`