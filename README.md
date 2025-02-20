# Its-showtime
# About the project
A full-stack web application that displays real-time movie showtimes and allows users to create accounts to save their favorite cinemas. Built with Spring Boot for the backend, integrating the MovieGlu API for live data, and MySQL for user data storage.

The backend was built using **Java** and **Spring Boot** and the frontend uses HTML, CSS, and Thymeleaf.
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
- The DAO tests must be done with the MYSQL server online. - (Don't forget to change the MYSQL credentials)



<!-- GETTING STARTED -->
## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Prerequisites

* Install MySQL
* Use ItsShowtimeScheme.sql in repo to create a database
 

### Installation

_Below is an example of how you can instruct your audience on installing and setting up your app. This template doesn't rely on any external dependencies or services._

1. Get an API Key at [https://movieglu.com/developers](https://movieglu.com/developers/)
2. Clone the repo
   ```sh
   git clone https://github.com/Totsua/its-showtime.git
   ```
3. Install NPM packages
   ```sh
   npm install
   ```
4. Enter your API settings in `api.properties`
   ```js
   api.client = ENTER CLIENT HEADER
   api.xkey = ENTER X-API-KEY HEADER
   api.authorization = ENTER AUTHORIZATION HEADER
   api.territory= ENTER TERRITORY HEADER
   ```
5. Change git remote url to avoid accidental pushes to base project
   ```sh
   git remote set-url origin github_username/repo_name
   git remote -v # confirm the changes
   ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- ROADMAP -->
## Roadmap

- [ ] Add style to all webpages
- [ ] Add user account settings
- [ ] Add user roles

<p align="right">(<a href="#readme-top">back to top</a>)</p>

