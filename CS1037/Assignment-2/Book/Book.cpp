// Implementation file for the Book class

#include "Book.h"
#include <string>
#include <iostream>

using namespace std;

// Constructor for Book instance
Book::Book(const string &name, const Author &author, double price, int qtyInStock) {
	this->name = name;
	this->author = author;
	
	// Invoke member functions to set price and stock quantity
	setPrice(price);
	setQtyInStock(qtyInStock);
}

// Get name of book
string Book::getName() const {
	return this->name;
}

// Get author of book (returns Author object)
Author Book::getAuthor() const {
	return this->author;
}

// Get price of book
double Book::getPrice() const {
	return this->price;
}

// Set new book price
void Book::setPrice(double price) {
	
	// Validate that price is a positive number
	if (price > 0.0)
		this->price = price;
	else
		cout << "Invalid price.\n";
}

// Get stock quantity
int Book::getQtyInStock() const {
	return this->qtyInStock;
}

// Set new stock quantity
void Book::setQtyInStock(int qtyInStock) {

	// Validate that stock quantity is non-negative integer
	if (qtyInStock >= 0)
		this->qtyInStock = qtyInStock;
	else {
		cout << "Quantity cannot be negative.\n";
		this->qtyInStock = 0;
	}
}

// Print information about book
void Book::print() const {
	cout << this->name << " by ";
	this->author.print();
}

// Get name of author 
string Book::getAuthorName() const {
	return this->author.getName();
}