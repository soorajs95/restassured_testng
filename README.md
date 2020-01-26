# restassured_testng

restassured_testng is a Java REST service testing framework using:

- [REST-assured](http://rest-assured.io/) library for REST service testing
- [TestNG](https://testng.org/doc/) testing framework
- [extentreports-testng-adapter](https://extentreports.com/docs/versions/4/java/testng.html) for TestNG test reporting

## Features

- **Easy test-data handling** using TestNG DataProviders instead of using external files to store test-data
- **Easy maintenance** as the test-data files, tests and implementations are separated into different packages

## Executing Tests

**Maven Commands:**

- Run the project - `mvn clean test`
- Run test method - `mvn -Dtest=SearchRepoTest#searchByOrgAndVerifyResponse test`

## Reports

Reports will be generated in `target/HtmlReport/ExtentHtml.html`