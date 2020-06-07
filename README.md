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
  
