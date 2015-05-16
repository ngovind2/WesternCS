// Implementation file for the Author class

#include <iostream>
#include <string>
#include "Author.h"

using namespace std;

// Returns name of author
string Author::getName() const {
	return name;
}

// Returns email address of author
string Author::getEmail() const {
	return email;
}

// Sets a new address for author's email
void Author::setEmail(const string &email) {

	// Validate to ensure that '@' exists and is not at beginning or end of string
	if (email.find("@") > 0 && email.find("@") < email.length() - 1)
		this->email = email;

	// If invalid, warn user and set address to empty string
	else {
		cout << "Invalid email.\n";
		this->email = "";
	}
}

// Returns gender of author
char Author::getGender() const {
	return gender;
}

// Prints information of author
void Author::print() const {
	cout << name << " (" << gender << ") at " << email << ".\n";
}

// Constructor
Author::Author(const string &name, const string &email, char gender) {

	// Initializes name of author
	this->name = name;

	// Calls member function to set email address
	setEmail(email);

	// Validates gender is either 'm' or 'f'; otherwise, gender becomes 'u'
	if (gender == 'm' || gender == 'f')
		this->gender = gender;
	else
		this->gender = 'u';
}

// Default constructor
Author::Author() {}