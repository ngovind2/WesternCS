// Game where user attempts to guess a randomly chosen number

#include <iostream>
#include <ctime>
#include <cstdlib>
#include <string>
using namespace std;

const int SIZE = 25;            // Array size
int attempts1, attempts2;       // Number of guesses

// Function prototypes
void selectionSort(int[]);
int binarySearch(const int[], int);
int numGuess();
int posGuess();
void shuffleArray(int[]);

// Client program
int main() {
	
	int randomNum, index, guess, indexGuess;
	string replay;

	int myArray[SIZE];						

	srand(int(time(0)));                    // Pseudo-random number generator
	for (int i = 0; i < SIZE; i++) {        // Loop through array
		myArray[i] = rand() % 50 + 1;   // Generate random numbers between 1-50 & store in array
	}

	// Beginning of 'Guess My Number' game
	do {
		attempts1 = 0;                  // Reset count for number of guesses
		attempts2 = 0;
		selectionSort(myArray);         // Invoke function to sort array in ascending numerical order (for binary search)
		
		do {
			randomNum = rand() % 51 + 5;                    // Generate random number between 5-55
			index = binarySearch(myArray, randomNum);       // Search array for random number; positive return value indicates number is found
		} while (index < 0);                                    // Re-generate random number until a match is found in array
		

		cout << "Please choose a number between 1 and 50:\n";
		do {											
			guess = numGuess();                             // Prompt user to guess number

			if (guess < myArray[index]) {                   // Compare guess to index and output corresponding message
				cout << "Choose a larger value:\n";
			}
			else if (guess > myArray[index]) {
				cout << "Choose a smaller value:\n";
			}
			else {
				cout << "Bravo!! You guessed my number in " << attempts1 << " attempt(s).\n";
			}
		} while (guess != myArray[index]);    // Continue until user guesses correctly


		cout << "Now, try to guess the location of this number. Choose a number between 0 and 24: ";
		do {
			indexGuess = posGuess();                    // Prompt user to guess index

			if (indexGuess < index) {                   // Compare guess to index and output corresponding message
				cout << "Choose a larger value:\n";
			}
			else if (indexGuess > index) {
				cout << "Choose a smaller value:\n";	
			}
			else {
				cout << "Bravo!! You guessed my number in " << attempts1 << " attempt(s) and the location in " << attempts2 << " attempt(s).\n";
			}
		} while (indexGuess != index);          // Continue until user guesses correctly


		cout << "Game over. Do you want to play again? Press 'y' for yes or any other key for no. ";
		cin >> replay;

		if (replay == "y" || replay == "Y")         // If user wants to play again, shuffle array
			shuffleArray(myArray);

	} while (replay == "y" || replay == "Y");           // Repeat game if user chooses 'y'

	cout << "Thanks for playing.\n";
	return 0;
}

// Additional Functions
// Selection Sort: sorts an array of integers in ascending numerical order
void selectionSort(int myArray[]) {
	int minPos, temp;

	for (int i = 0; i < SIZE - 1; i++) {                    // Loop through array
		minPos = i;

		for (int j = i + 1; j < SIZE; j++){			
			if (myArray[j] < myArray[minPos])       // Compare an element to all others in array
				minPos = j;
		}

		if (minPos != i) {                              // If another element is smaller, swap positions
			temp = myArray[i];
			myArray[i] = myArray[minPos];
			myArray[minPos] = temp;
		}
	}
}

// Binary Search: searches array for a specified number
int binarySearch(const int myArray[], int number) {
	int low(0), high(SIZE - 1), mid;
	while (high >= low) {
		mid = (low + high) / 2;                 // Establish middle of array as start point for search
		if (number < myArray[mid])		// If number is greater than midpoint element, refine search to latter half of array
			high = mid - 1;
		else if (number > myArray[mid])		// If number is less than midpoint element, refine search to former half of array
			low = mid + 1;
		else
			return mid;                     // Return midpoint index if number equates to midpoint element
	}
	return -(low - 1);                              // If number not found, return negative value of theoretical index
}

// Number Guess: prompt user to guess a number between 1-50
int numGuess() {
	int guess;

	do {
		attempts1 += 1;                                 // Track attempts
		cin >> guess;								
		if (guess < 1 || guess > 50) {                  // Validate input
			cout << "Please try again:\n";			
		}
	} while (guess < 1 || guess > 50);			// Continue until suitable guess is chosen

	return guess;
}

// Position Guess: prompt user to guess the array index of number (between 0-24)
int posGuess() {
	int indexGuess;

	do {
		attempts2 += 1;                                 // Track attempts
		cin >> indexGuess;
		if (indexGuess < 0 || indexGuess > 24) {	// Validate input
			cout << "Please try again:\n";
		}
	} while (indexGuess < 0 || indexGuess > 24);		// Continue until suitable guess is chosen

	return indexGuess;
}

// Shuffle Array: randomly swap elements in an array
void shuffleArray(int myArray[]) {
	for (int i = 0; i<25; i++) {
		int j = rand() % 25;		        // Generate random index number

		int temp = myArray[i];		        // Swap element from current index to random index
		myArray[i] = myArray[j];
		myArray[j] = temp;
	}
}
