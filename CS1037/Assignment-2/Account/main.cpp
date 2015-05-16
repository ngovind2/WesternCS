// Manipulate account objects

#include "Account.h"
#include <iostream>

using namespace std;

int main() {

	Account Account1(111, 1000);																						// Create Account object
	cout << "Account number is " << Account1.getAccountNumber() << ".\n";		// Get account number
	cout << "Balance is $" << Account1.getBalance() << ".\n";								// Get account balance
	
	Account1.setBalance(2000);				// Set the account balance to $2000
	Account1.credit(500);							// Deposit $500 into account
	Account1.debit(1500);							// Withdraw $1500 from account
	cout << "Updated - ";
	Account1.print();									// Print information about the account

	Account Account2(112);																								// Create Account object with default balance (0.0)
	cout << "Account number is " << Account2.getAccountNumber() << ".\n";	// Get account number
	cout << "Balance is $" << Account2.getBalance() << ".\n";							// Get account balance
	
	Account2.setBalance(1000);				// Set the account balance to $1000
	Account2.debit(1200);							// Withdraw $1200 from account
	Account2.credit(800);							// Deposit $800 into account
	cout << "Updated - ";
	Account2.print();									// Print information about the account

	return 0;

}
