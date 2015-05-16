// Manipulates author and book objects

#include <iostream>
#include <string>
#include "Author.h"
#include "Book.h"

using namespace std;

int main() {

	Author author("Dan Brown", "dan.brown@gmail.com", 'j');	
	author.print();																									
	author.setEmail("@john.com");																		
	cout << "Author's name is " << author.getName() << ".\n";				
	cout << "Author's gender is " << author.getGender() << ".\n";	
	cout << "Author's email is " << author.getEmail() << ".\n\n";	

	Book book("The DaVinci Code", author, 30.99, 50);							
	book.setQtyInStock(-55);																			
	book.getAuthor();																							
	cout << "Book's author is " << book.getAuthorName() << ".\n";		
	cout << "Book's title is " << book.getName() << ".\n";				
	cout << "Book's price is $" << book.getPrice() << ".\n";				
	cout << "Stock quantity is " << book.getQtyInStock() << ".\n";	
	book.setPrice(25.49);																						
	cout << "Updated information - ";
	book.print();																								

	return 0;
}
