// Printing ASCII values of characters; implemented using a linked list

#include <iostream>
using namespace std;

// Node struct definition
struct Node {

	char character;
	int ascii;
	Node * next;

	// Constructor
	Node(char ch, Node * ptr = NULL) {

		character = ch;
		ascii = (int)ch;
		next = ptr;

	}
};

// Client program
int main() {

	char input;
	Node * temp, * temp2, * head = NULL;

	// Prompt for user input
	cout << "Please enter a series of characters, each on a separate line. Enter '0' to stop. \n";

	// Loop until user signals termination
	for (; ;) {

		cin >> input;

		// If user enters termination signal, break out of loop
		if (input == '0')
			break;

		else {

			// Dynamically allocate node
			temp = new Node(input);

			// If list is empty, new node becomes head of list
			if (head == NULL)
				head = temp;

			// Otherwise, node is added to end of list
			else {

				// Traverse the list to the last node
				temp2 = head;
				while (temp2->next != NULL)
					temp2 = temp2->next;

				// Set the last node's pointer to the newly created node
				temp2->next = temp;
			}
		}
	}

	temp2 = head;

	while (temp2 != NULL) {

		// Print values
		cout << "The ASCII value for " << temp2->character << " is " << temp2->ascii << ".\n";

		head = head->next;		// Reset head of list
		delete temp2;			// Delete node
		temp2 = head;			// Reset pointer to new value; avoids dangling pointer
	}

	return 0;
}