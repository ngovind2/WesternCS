// Class specification (header) file for Book

#ifndef BOOK_H
#define BOOK_H

#include "Author.h"
#include <iostream>
#include <string>

using namespace std;

class Book {

private:
	string name;	// Member variables
	Author author;
	double price;
	int qtyInStock;

public:
	Book(const string &, const Author &, double, int qtyInStock = 0);	// Constructor
	string getName() const;											
	Author getAuthor() const;
	double getPrice() const;
	void setPrice(double);
	int getQtyInStock() const;
	void setQtyInStock(int);
	void print() const;
	string getAuthorName() const;

};

#endif
