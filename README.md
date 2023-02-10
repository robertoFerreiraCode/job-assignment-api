# job-assignment-api

## Description

Full stack mock Job Assignment application which allows users to assign temporary workers to posted jobs. The API is built using the Java Spring Boot framework, utilising mySQL to store worker and job details as well as Spring Security to handle authentication. The API interacts with the React, Typescript front end configured with Vite, in which the user can login and return all jobs or jobs based on parameters, as well as available workers, and assign jobs to workers.

## Setup

### Requirements

-   JDK 17+
-   MySQL Server

The project files expect a MySQL Server running on localhost on port 3306 with username root and password password

1. Clone this repository with `git clone`
2. Run from project files with `./mvnw spring-boot:run`

## Endpoints

### Jobs

`POST` `/jobs` - Creates a job (requires admin role)

`PATCH` `/jobs/{id}` - Updates job

`GET` `/jobs` - Lists all jobs

`GET` `/jobs?assigned={true|false}` - List all jobs, filtered by whether a job is assigned to a temp or not

`GET` `/jobs/{id}` - Returns a specific job

### Temps

`POST` `/temps` - Create a temp (requires admin role)

`GET` `/temps` - List all temps

`GET` `/temps?jobId={jobId}` - List temps that are available for a job based on the jobs date range

`GET` `/temps/{id}` - Returns a specific temp
