# Wildlife Sighting Tracker
#### A Java application that allows users to track wildlife sightings in an area for the purposes of an environmental impact study on the clearcutting of a nearby forest.

## Description

The Java application allows users to track wildlife sightings in an area by recording animal data such as whether they are endangered or not, their species and sighting data; the animal sighted, the location, who sighted the animal and the time of reporting. Moreover, a user can record multiple animals/species sighted at the same time. In addition to this, a user can view sightings based on location and the rangers that reported it. Form validation has also been included to validate user input. Currently, the list of rangers and locations are predefined thus readonly. The application also contains JUnit tests that check that the backend logic is working as expected in various scenarios as listed in the Behavior Driven Development section below. That is, in the development of the application TDD(Test Driven Development) has been used alongside BDD(Behavior Driven Development). 

#### By **[Lynn Nyangon](https://github.com/AnnaL001)**

## Setup/Installation Requirements

- Using a mobile device/laptop ensure you have access to stable internet connection
- To access the Java application's code from your GitHub repository, you can fork the repository main's branch via the 'Fork' button.
- To access the Java application's code locally, you can clone the main branch or download the ZIP folder via the 'Code' button
- Once locally, you can view/run the Java application's code via a text editor(VS Code or Sublime Text) or an IDE(IntelliJ).
- In the case of IntelliJ, to navigate you can reference their documentation https://www.jetbrains.com/help/idea/getting-started.html
- Commands to set up the database are as listed below: <br>
  ```
    /* In psql */
    CREATE DATABASE wildlife_tracker;
    \c wildlife_tracker;
    CREATE TABLE animals (id serial PRIMARY KEY, image varchar, name varchar, health varchar, age varchar, category varchar);
    CREATE TABLE sightings (id serial PRIMARY KEY, locationid int, rangerid int, reportedat timestamp);
    CREATE TABLE rangers (id serial PRIMARY KEY, name varchar, badge varchar UNIQUE, phone varchar UNIQUE, email varchar UNIQUE);
    CREATE TABLE locations (id serial PRIMARY KEY, name varchar, description varchar);
    CREATE TABLE species (id serial PRIMARY KEY, name varchar);
    CREATE TABLE animals_sightings (id serial PRIMARY KEY, animalid int, sightingid int);
    CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;
  ```
- Otherwise to view the web application navigate to the link below <br>
[Live Site](https://anna-wildlife-sighting-tracker.herokuapp.com/)
  
## Behavior Driven Development(BDD)
| **Behavior**                              | **Input Example**                           | **Output**                                                         |
|-------------------------------------------|:--------------------------------------------|:-------------------------------------------------------------------|
| Add an endangered animal     | name=Lion King, image=https://wildlife_tracker/image.jpg, category=Endangered, health=healthy age=young |  Animal is added and user redirected to animal list page    |
| Add a thriving animal   | name=Mindy,  image=Lion King, speciesId=1 image=https://wildlife_tracker/image.jpg, category=Thriving  | Animal is added and user redirected to animal list page |
| Add a sighting  | locationId=1, rangerId=1 | Sighting added and user redirected to sighting list page   |
| Add animals sighted to sighting | animalId=1, sightingId=1   | Selected animal sighted added to sighting data and user redirected to sighting details page|
| Read an endangered animal's data | id=1  | User redirected to endangered animal profile page |   
| Read a thriving animal's data | id=1 | User redirected to thriving animal profile page | 
| Read a sighting's data | id=1 | User redirected to sighting's detail page |
| Read all endangered animals' data | No input required  | User redirected to endangered animal list page |
| Read all thriving animals' data | No input required | User redirected to thriving animal list page |
| Read all sightings' data | No input required |  User redirected to sightings' list page |
| Read sighting's data based on location | locationId=1 |  User redirected to location's page and can view what sightings have been reported at specified location |
|Read sighting's data based on ranger| rangerId=1 | User redirected to ranger's page and can view sightings reported by each ranger |
| Update an endangered animal's data | name=Cecil, speciesId=2 image=https://wildlife_tracker/image.jpg, category=Endangered, health=ill age=young | Endangered animal's data is updated and user redirected to endangered animal's profile page |
| Update a thriving animal's data | name=Mindy Starr,  image=Lion King, speciesId=1 image=https://wildlife_tracker/image.jpg, category=Thriving  | Thriving animal's data is updated and user redirected to thriving animal's profile page |
| Update s sighting's data | locationId=1, rangerId=3 | Sighting data updated and user redirected to sighting's details page |
| Update animals sighted to sighting data | animalId=1, sightingId=4 | Sighted animals' data recorded alongside other sighting details |
| Delete an endangered animal data | animalId=1 | Endangered animal's data is deleted and user redirected to animal list page |
| Delete a thriving animal's data | animalId=1 | Thriving animal's data is deleted and user redirected to animal list page |
| Delete a sighting's data | id=1 | Sighting's data is deleted and user redirected to sighting list page |


## Dependencies

- JUnit 5 
- Spark Framework
- Handlebars 
- Gradle
- Maven
- Joda Time

## Technologies Used

- Java 
- Bootstrap
- HTML
- CSS
- Postgresql

## Known bugs
The form selects do not automatically set the selected value as the current value in the database when updating data.

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
