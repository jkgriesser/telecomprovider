# Telecom Provider - Phone Number API
This is a Spring Boot REST application running on default port 8080.

## REST Endpoints
The following services are provided:

* _/api_ &rarr; Simple greeting
* _/api/phonenumbers_ &rarr; Get all phone numbers
* _/api/customers/{customerId}/phonenumbers_ &rarr; Get all phone numbers of a single customer
* _/api/phonenumbers/{phoneNumber}/activate_ &rarr; Activate a phone number

## Start
Note: These instructions presume you already have **JDK 8** installed.

Run `./gradlew bootRun` to launch the web application.

## Test
To test the application, run `./gradlew check`.

## Todo
* Security
* Database integration
* Logging
* Full API contract
* Rate limiting and limiting query results (i.e. pagination)
* Results sorting
* JUnit 5 integration (e.g. @DisplayName annotations for easy-to-read test descriptions).
* Use Google's libphonenumber library (https://github.com/googlei18n/libphonenumber) to parse phone numbers
* Use Lombok for data/model classes

etc.
 