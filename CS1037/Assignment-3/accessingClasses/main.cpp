// Accessing members in other classes

#include <iostream>
using namespace std;

// Class definition
class class2 {

public:
	int y;						// Member variable
	class2() { y = 10; }		// Default constructor

	void print() const {		// Member function: prints value of y
		cout << y << endl;
	}
};


// Class definition
class class1 {

public:
	int x;						// Member variable
	class1() { x = 0; }			// Default constructor

	void accessclass2(class2 obj) const {	// Member function: prints value of object's member variable
		obj.print();
	}
};

int main() {
	class1 obj1;					// Declare two objects of each class
	class2 obj2;

	obj1.accessclass2(obj2);		// Invoke member function

	return 0;
}
