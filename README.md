# Entry for Vanhack 2018 event - Team #4 Cocobun

This project demonstrates one part of automated workflow that BabyGoRound envisioned. 

## Build and Installation

### Prerequisites

* Java 9 or higher
* Postgresql 10 or higher

### Command-line Instructions

* Build
```bash
./gradlew clean build
```

* Run
```bash
./gradlew bootRun
```

## Technology

* Spring Boot
 * Spring Fox for generating the swagger doc, served at -
 * xMatters integration
 * Twilio integration
* CD Pipeline hosted on AWS
 * Jenkin 
 * CodeBuild and CodeDeploy
 * Slack-bot integration
* Vue.js 
* Twitter Boostrap
** Material design

## Consideration

We did not set up front-end asset pipeline in order to quickly iterate UI changes. Serveral libraries imported on run-time are designed for development, and should not be used in production.  

