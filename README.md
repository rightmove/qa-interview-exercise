standalone-java-test
================

This is a new web service for providing residential property information. 

Endpoints
---------
`GET /property?postcode=SO33 9SC` - filter properties by postcode

Tasks
-------
1. Finish implementing the `GET /property` endpoint so that it works as expected and all tests pass
2. Add a feature to `GET /property` to filter by `propertyType`  

Running
-------
To run the application via the command line - `./gradlew bootRun`

To build the application via the command line - `./gradlew build`

To test the application via the command line - `./gradlew test`

Technologies
------------
* Gradle
* Spring Boot 2
* JUnit 5

You can use any other technologies to complete the task