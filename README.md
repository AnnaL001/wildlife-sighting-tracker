# Wildlife Sighting Tracker
#### A Java application that allows users to track wildlife sightings in an area for the purposes of an environmental impact study on the clearcutting of a nearby forest.

## Description

The Java application allows users to track wildlife sightings in an area by recording animal data such as whether they are endangered or not, their species and sighting data; the animal sighted, the location, who sighted the animal and the time of reporting. Moreover, a user can record multiple animals/species sighted at the same time. In addition to this, a user can view sightings based on location and the rangers that reported it. Form validation has also been included to validate user input. The application also contains JUnit tests that check that the backend logic is working as expected in various scenarios as listed in the Behavior Driven Development section below. That is, in the development of the application TDD(Test Driven Development) has been used alongside BDD(Behavior Driven Development). 

#### By **[Lynn Nyangon](https://github.com/AnnaL001)**

## Setup/Installation Requirements

- Using a mobile device/laptop ensure you have access to stable internet connection
- To access the Java application's code from your GitHub repository, you can fork the repository main's branch via the 'Fork' button.
- To access the Java application's code locally, you can clone the main branch or download the ZIP folder via the 'Code' button
- Once locally, you can view/run the Java application's code via a text editor(VS Code or Sublime Text) or an IDE(IntelliJ).
- In the case of IntelliJ, to navigate you can reference their documentation https://www.jetbrains.com/help/idea/getting-started.html
- Commands to set up the database are as listed below: <br>
  ```
    In psql
    CREATE DATABASE wildlife_tracker;
    CREATE TABLE animals (id serial PRIMARY KEY, image varchar, name varchar, health varchar, age varchar, category varchar);
    CREATE TABLE sightings (id serial PRIMARY KEY, locationid int, rangerid int, reportedat timestamp);
    CREATE TABLE rangers (id serial PRIMARY KEY, name varchar, badge varchar UNIQUE, phone varchar UNIQUE, email varchar UNIQUE);
    CREATE TABLE locations (id serial PRIMARY KEY, name varchar, description varchar);
    CREATE TABLE species (id serial PRIMARY KEY, name varchar);
    CREATE TABLE animals_sightings (id serial PRIMARY KEY, animalid int, sightingid int);
    CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;
  ```
- Otherwise to view the web application navigate to the link below <br>
  
## Behavior Driven Development(BDD)

## Dependencies

- JUnit 5 
- Spark Framework
- Handlebars 

## Technologies Used

- Java 
- Bootstrap
- HTML
- CSS
- Postgresql

## Support and contact details

In case of any queries you can reach out via email; lynn.nyangon@gmail.com

### License

MIT License
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.<br>
Copyright (c) 2022 **Lynn Nyangon**
