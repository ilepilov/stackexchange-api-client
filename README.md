# stackexchange-api-client

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

This is an attempt to build a StackExchange API client.

### Built With

* [Java 11](https://openjdk.java.net/projects/jdk/11/)
* [Gradle](https://gradle.org/)
* [Spring](https://spring.io/)
* [Retrofit](https://square.github.io/retrofit/)
* [Lombok](https://projectlombok.org/)

## Getting Started

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/ilepilov/stackexchange-api-client.git
   ```
2. Build the project
   ```sh
   ./gradlew clean build
   ```
3. Execute the generated JAR from $project/build/libs
   ```sh
   java -jar stackexchange-client-0.0.1-SNAPSHOT.jar
   ```
