// Class specification (header) file for Author

#ifndef AUTHOR_H
#define AUTHOR_H
#include <string>

using namespace std;

class Author {
private:
	string name;	// Member variables
	string email;
	char gender;
	
public:
	Author();					// Default constructor
	Author(const string &, const string &, char);	// Overloaded constructor
	string getName() const;						
	string getEmail() const;
	char getGender() const;
	void setEmail(const string &);
	void print() const;
};

#endif
