# NewportReuseAndRecycle project

NewportReuseAndRecycle project aims to improve reuse and recycle in Newport area.

## Installation and run

Clone the project

CD to the project path, run 

```bash
./gradlew bootJar
```
to build an executable .jar file,

Run src/main/resources/create-db.sql to generate the prod database

CD to `build/libs/`, run 

```bash
java -jar NewportReuseAndRecycle-0.0.1-SNAPSHOT.jar
```
to run the application with prod database (MariaDB).

Go to `http://localhost:8080/login` to login, use the following details:

username: admin

password: admin

## Switch between dev and prod databases

### dev database:

Go to `application.properties`, change the following to point to dev database:
```java
spring.profiles.active=dev
```

### prod database:
Run `src/main/resources/mysql-schema.sql` and `src/main/resources/mysql-data.sql` in sequence to populate data into prod database, change the following to point to the prod database:
```java
spring.profiles.active=prod
```
