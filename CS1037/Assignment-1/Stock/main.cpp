// Manipulate stock objects

#include "Stock.h"				
#include <iostream>
#include <string>
using namespace std;

/******************** Function prototypes ********************/
bool isValid(int);
bool isValid(double);

/********************** Client program ***********************/
int main() {

	int itemNum, quant;
	double price;
	bool valid, fail;

	Stock stock1;				// Object initialized by default constructor
	Stock stock2(3, 4, 5.50);		// Object initialized by overloaded constructor

	// Print information about each object
	cout << "Two Stock objects have been created: stock1 and stock2.\n";
    
	cout << "Stock1 currently has " << stock1.getItemNumber() << " item numbers. Its quantity is " << stock1.getQuantity() << " and its cost is " << stock1.getCost() << ".\n";
    
	cout << "Stock2 currently has " << stock2.getItemNumber() << " item numbers. Its quantity is " << stock2.getQuantity() << " and its cost is " << stock2.getCost() << ".\n\n";

	// Prompt user to change stock1
	cout << "Here's your chance to set new values for stock1.\n";

	cout << "Choose a new item number for stock1:\n";	// Set new value for item number
	do {
		cin.clear();            // Reset error flags for cin
		cin >> itemNum;	        // Get user input
		fail = cin.fail();      // Check status of last cin execution

		cin.clear();            // Reset error flags for cin
		cin >> itemNum;         // Get user input
		fail = cin.fail();      // Check status of last cin execution

		if (fail)	        // Input validation
			cout << "Please enter a non-negative integer:\n";

		while (fail) {
			cin.clear();                                            // Reset error flags for cin
			cin.ignore(numeric_limits<streamsize>::max(), '\n');    // Discard content of input sequence
			cin >> itemNum;                                         // Prompt user to re-enter a proper value
			fail = cin.fail();                                      // Re-validate
		}

                                                               // Input validation
		if (isValid(itemNum)) {                         // Check that input is positive
		                                                // Input validation 
		if (isValid(itemNum)) {                         // Check that input is positive
			stock1.setItemNumber(itemNum);          // If so, change value of attribute
			valid = true;
			cout << "Changes made!\n";
		}
		else {
			valid = false;								
			cout << "Please enter a non-negative integer:\n";
		}
	} while (!valid);	// Loop ends when non-negative input has been entered


	cout << "Choose a new quantity for stock1:\n";	        // Set new value for quantity
	do {												
		cin.clear();
		cin.ignore(numeric_limits<streamsize>::max(), '\n');
		cin >> quant;
		fail = cin.fail();

		if (fail)	// Input validation
			cout << "Please enter a non-negative integer:\n";

		while (fail) {
			cin.clear();
			cin.ignore(numeric_limits<streamsize>::max(), '\n');
			cin >> quant;
			fail = cin.fail();
		}

		if (isValid(quant)) {                   // Input validation
			stock1.setQuantity(quant);      // Check that input is positive
			valid = true;                   // If so, change attribute to new value
			
		if (isValid(quant)) {                   // Input validation
			stock1.setQuantity(quant);      // Check that input is positive
			valid = true;                   // If so, change attribute to new value
			cout << "Changes made!\n";
		}
		else {
			valid = false;
			cout << "Please enter a non-negative integer:\n";
		}
	} while (!valid);	// Loop ends when valid input has been entered

	cout << "Choose a new cost for stock1:\n";      // Set new value for cost
	cout << "Choose a new cost for stock1:\n";	// Set new value for cost

	do {												
		cin.clear();
		cin.ignore(numeric_limits<streamsize>::max(), '\n');
		cin >> price;
		fail = cin.fail();

		if (fail)	// Input validation
			cout << "Please enter a non-negative decimal number:\n";

		while (fail) {
			cin.clear();
			cin.ignore(numeric_limits<streamsize>::max(), '\n');
			cin >> price;
			fail = cin.fail();
		}
		                                        // Input validation
		if (isValid(price)) {                   // Check that input is positive
			stock1.setCost(price);          // If so, change value of attribute
			                                // Input validation
		if (isValid(price)) {                   // Check that input is positive
			stock1.setCost(price);          // If so, change value of attribute
			valid = true;
			cout << "Changes made!\n";
		}
		else {
			valid = false;
			cout << "Please enter a non-negative decimal number:\n";
		}
	} while (!valid);	// Loop ends when valid input has been entered

	// Display updated information about stock1 object
	cout << "The new item number of stock1 is " << stock1.getItemNumber() << ".\n";
	cout << "The new quantity of stock1 is " << stock1.getQuantity() << ".\n";
	cout << "The new cost of stock1 is " << stock1.getCost() << ".\n";
	cout << "Based on this, the total cost of stock1 is $" << stock1.getTotalCost() << ", compared to stock2's total cost, $" << stock2.getTotalCost() << ".\n";

	return 0;	// Destructor called upon return
}

/********************* Additional functions **********************/

// Is Valid: confirms that user has inputted a non-negative integer
bool isValid(int input) {
	bool valid;

	if (input >= 0)
		valid = true;
	else
		valid = false;

	return valid;
}

// Is Valid: confirms that user has inputted a non-negative floating-point value
bool isValid(double input) {
	bool valid;

	if (input >= 0.00)
		valid = true;
	else
		valid = false;

	return valid;
}

// Note - similar functionality could have been achieved using function templates
