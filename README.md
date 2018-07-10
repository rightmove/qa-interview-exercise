standalone-java-test
================

This project is a partially completed api application created using Spring Boot. Currently it contains a single endpoint in the `PropertyController` class to return properties for a given postcode.

Tasks
-------
1. Finish implementing the `/property` endpoint so that it works as expected and all tests pass
2. Add a feature to `/property` to filter by `propertyType`
3. Create an endpoint to to find the average property value of all properties 
4. Create a new endpoint to return all property images  

Running
-------
`./gradlew bootRun`