# CineScribe 🎥

a terminal-based java program to search and  to provide information about movies and actors.

It fetches data from both the IMDB and Actor API to provide details such as movie plot, ratings, actors, director, box office, net worth of actors, etc.

## Description

The Movie and Actors Info System is a Java-based application that aims to offer users comprehensive information about movies and actors.

To achieve this, it leverages external APIs, including the OMDB API for movie data and API Ninjas for actor information. The program is structured around three main classes: Movie, Actors, and Main.

### Main Class

* It has a menu that the user can choose weather an actor or a movie to get their information separately. ( or enter number 3 to exit )
* When the information of a movie is displayed you can choose one of its actors and see him/her information too. ( or you can return to main menu by entering any key )

### Movie Class
The Movie class handles movie-related functionalities.
* It has 15 attributes including movieTitle, year, releaseDate, rated, runtime, genre, director, writer, actorsList, plot, language, country, awards, imdbVotes, rating.
* I implemented a setAll method to set the value of each attribute.
* I also implemented a get method for each of the new attributes.
* It has an errorCheck method (see Error Handling section)

### Actors Class
The Actors class handles actor-related functionalities.
* It has 7 attributes including name, netWorth, gender, nationality, occupation, isAlive, dateOfDeath.
* I implemented a setAll method to set the value of each attribute.
* I also implemented a get method for each of the new attributes.
* It has an errorCheck method (see Error Handling section)

## Error Handling
* If the connection with api is not established, the following text will be displayed (due to the internet being cut off, etc.)
```
Error: Could not connect to api!
```
* If user enters a movie or an actor name that is not in the api database, the app displays message below and redirects the user to the menu. 
```
Error: Movie not found!
```
or
```
Error: Actor not found!
```
* Both Actors and Movie classes has an errorCheck method. In Movie class it searches for Response key in the JSON and in Actors class checks if it's equal to "[]" or not.

## Getting Started

### Dependencies

* Java SDK Runtime Environment 8.0 or later

To make sure it is installed, enter the following command in the terminal.
```
java --version
```

### Installing

* You can download program executable .jar file in the release page of this project.
* You can clone this repository to access both .java source files and .jar file.

### Executing program

* Open Terminal in directory has .jar file.
* Run the program using the following command:
```
java -jar CineScribe-1.0.jar
```

## Help
* Ensure a stable internet connection.
* Verify the correctness of the movie or actor name entered.
* You could contact the authors down below for more help.

## Authors
osumyy

[Contact on Telegram](https://t.me/osumyy)

## Version History

* 1.0
    * Initial Release