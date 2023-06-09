# Its-showtime
 This is a work in progress. 
# Dependencies
- Java - JDK 17

- Spring Boot 3

- Spring Boot Thymeleaf

- Spring Boot Web

- Spring Boot DevTools

- MySQL 

- Spring JDBC

- Spring Test

- Spring Validation

- Spring Security

# Frontend Technology
- HTML
- CSS

# Current Work
- There is login and registration however no functionality whilst logged in.
- There is no CRUD at the moment (in terms of the user cinema saving etc)
- The APIService has logic to display a message if the API status code is not "200"
-- To see it, simply break a API header in the apiCaller method (APIController)
-- Easiest way is removing "Basic" from .header("Authorization")
-- Or type in a random number into the cinema/film ID in the closestShowing or cinemaShowTimes url
-- The DAO tests must be done with the MYSQL server online. 