// Stores and prints information about movies

#include <iostream>
#include <string>
using namespace std;

// Structure declaration
struct MovieData {
	string title;
	string director;
	int yearReleased;
	int runningTime;

	// Constructor
	MovieData(string t, string d, int y, int r) {
		title = t;
		director = d;
		yearReleased = y;
		runningTime = r;
	}
};

// Function prototypes
void displayMovie(MovieData);

// Client program
int main() {

	MovieData movie1("Batman", "Christopher Nolan", 1966, 104);	// Create & initialize MovieData variables
	MovieData movie2("The Hunger Games", "Gary Ross", 2012, 142);	

	displayMovie(movie1);	// Display information about each movie								
	displayMovie(movie2);		

	return 0;
}

// Additional functions
// Display Movie: outputs information about a specified movie
void displayMovie(MovieData movie) {
	cout << movie.title << " was directed by " << movie.director << " in " << movie.yearReleased << ".";
	cout << " It is " << movie.runningTime << " minutes long.\n";
}
