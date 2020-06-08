# Microservices Java Project
A small Microservices Java Project to manage Users, store data into a Database and that is able to send events between services

## Getting started
To get this Application running locally:

**Spring Boot**
- Clone/Download the Java project
- You can import the project on your favorite IDE:
   - On Eclipse: File -> Import -> Existing Maven Project
   - Right Click on the directory and Run as Maven install
   - Then run the Application.java as Java Application or as JUnit Test
- Alternately you can create an executable jar; Go to pom.xml directory and run:
  ```
  mvn install
  java -jar path/target/backend-project-0.0.1-SNAPSHOT.jar
  ```
Spring Boot Application will run on `localhost:8080`.  

**RabitMQ**  
It's also important to set up and run a AMQP server.  
On MacOS with Homebrew:
```
brew install rabbitmq
```
Otherwise, download it here: https://www.rabbitmq.com/download.html.  
Then:
```
rabbitmq-server
```
If you have any problem with that comand try:
```
rabbitmq-server/usr/local/sbin/rabbitmq-server
```

## Technologies
The main technologies used for this project are:
  - Java
  - Spring Boot
  - Hibernate
  - H2 Database
  - RabbitMQ
  - JUnit
  - Maven
  
## Functionality overview
The Java application exposes REST Web Services to perform **CRUD** operations of User entities.  
The Database used for this application is **H2**, which is an in-memory database.  
**Spring Boot** and **Hibernate-JPA** are integrated togheter for a better application implementation, the database is automatacly initialised by Spring and the **ORM** pattern is used, mapping the database tables with the java classes.  
The service is also able to send events after an operation was completed so that other services (not implemented in this application but that could be implemented in a real production enviroment) can be updated if they are interested in the User service.  
The service can send events thanks to an Event Producer created with **RabbitMQ**.  
The Application is also provided of Unit and Integration tests as well as a simple health check.  
The *@SpringBootTest* annotaion and **JUnit 5** are used to write the tests.  
**Maven** to download all the dependencies.  
