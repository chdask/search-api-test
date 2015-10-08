# Search API

## Stack
For the framework the following stack has been used:
* Spray/Akka (http://spray.io)
* scalaj-http for the rest client (https://github.com/scalaj/scalaj-http)
* json4s for JSON serialisation (https://github.com/json4s/json4s)
* scala-test for test
* swagger for API docs
* typesafe config for configuration

## Run the tests
From the root directory:

`sbt test` to run the tests

## Run the app
`sbt run`

## Run from package
1. `sbt one-jar`
2. `java -jar target/scala-2.11/nap-scala-test_2.11-1.0-one-jar.jar`

## Test from browser
`http://localhost:8080/?q=blend`

## Test from swagger
`http://localhost:8080/swagger-ui`

## Test from command
curl `http://localhost:8080/?q=blend`