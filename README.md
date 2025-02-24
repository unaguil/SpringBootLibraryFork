Sprint initializer: https://start.spring.io/
mvn clean install
mvn spring-boot:run

GET http://localhost:8080/api/books

POST http://localhost:8080/api/books
Content-Type: application/json

{
 "title": "Spring Boot in Action",
 "author": "Craig Walls",
 "isbn": "9781617292545"
}

DELETE http://localhost:8080/api/books/1

Very good explaination of the project
Reference: https://medium.com/@pratik.941/building-rest-api-using-spring-boot-a-comprehensive-guide-3e9b6d7a8951 
Building REST services with Sprint: https://spring.io/guides/tutorials/rest
Docker example with Sprint:https://medium.com/@yunuseulucay/end-to-end-spring-boot-with-mysql-and-docker-2c42a6e036c0

Visit Swagger interface at: http://localhost:8080/swagger-ui.html

Good example documenting how to generate Swagger APIs in Spring Boot: https://bell-sw.com/blog/documenting-rest-api-with-swagger-in-spring-boot-3/#mcetoc_1heq9ft3o1v 

Swagger UI: http://localhost:8080/swagger-ui.html
OpenAPI JSON: http://localhost:8080/v3/api-docs


1. Run all tests (Unit & Integration)

mvn test
This will execute all test cases in the src/test/java/ directory.

2. Run only Unit Tests
To run only unit tests, use:
mvn -Dtest=UserServiceTest,BookServiceTest test
mvn -Dtest=*ServiceTest test

3. Run only Integration Tests
To run only the integration test:
mvn -Dtest=LibraryIntegrationTest test

4. Run tests with detailed output
For more verbose output:
mvn test -Dspring-boot.run.profiles=test

This ensures your unit tests mock dependencies properly while integration tests interact with a real embedded server.

To run all tests, including performance tests, use:
mvn test

To run only performance tests, use:

mvn -Dtest=PerformanceTest test

Documentation on how to use Gatling: https://medium.com/thefreshwrites/getting-started-with-gatling-in-java-using-maven-b1664f6533d#:~:text=Gatling%20is%20a%20popular%20open,Gatling%20in%20Java%20using%20Maven. 