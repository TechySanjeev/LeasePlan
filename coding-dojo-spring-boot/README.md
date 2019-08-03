Spring Boot Coding Dojo
---

Welcome to the Spring Boot Coding Dojo!

### Introduction

This is a simple application that requests its data to [OpenWeather](https://openweathermap.org/) and store its result 
in a database. 

### Usage - Notes from developer
1. Clone the repository
2. Open with your favorite editor (Eclipse/Intellij)
3. Import the maven dependencies 
4. Obtain APi Key from OpenWeatherMap API and add the key to API_KEY
5. API Key and Weather API has been added to Application.yml to make it configurable
6. Specify the DB connection string details in Application.yml for PostGreSQL
7. Run and build the codebase
8. Run "Application.java" as Java Application to launch Springboot
9. Using a REST client, test the end points

###To run on local
mvn clean install (create the WAR)
can run the application using ./mvnw spring-boot:run. Or you can build the JAR file with ./mvnw clean package. 
Then you can run the JAR file:

java -jar target/coding-dojo-spring-boot-0.0.1-SNAPSHOT.jar

http://localhost:8080/weather?city=Amsterdam (endpoint for the application)