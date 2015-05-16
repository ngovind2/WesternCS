// Class implementation file for the Account program

#include "Account.h"
#include <iostream>
#include <iomanip>

using namespace std;

// Returns account number
int Account::getAccountNumber() const {
	return accountNumber;
}

// Returns account balance
double Account::getBalance() const {
	return balance;
}

// Sets new account balance
void Account::setBalance(double balance) {

	// Validate balance amount
	if (balance < 0.0)
		cout << "Incorrect balance entered.\n";
	else {
		this->balance = balance;
	}
}

// Deposits money into account
void Account::credit(double amount) {

	// Validate credit amount
	if (amount < 0)
		cout << "Error: you are attempting to withdraw money from the account.\n";
	else
		balance = balance + amount;
}

// Withdraws money from account
void Account::debit(double amount) {

	// Validate debit amount
	if (amount < 0.0)
		cout << "Please enter a positive amount to withdraw.\n";
	else if (amount > balance)
		cout << "Amount withdrawn exceeds the current balance.\n";
	else
		balance = balance - amount;
}

// Displays information about account
void Account::print() const {
	cout << "Account no: " << accountNumber << " Balance = $" << fixed << showpoint << setprecision(2) << balance << ".\n";
}

// Constructor
Account::Account(int accountNumber, double balance) {

	// Validate account number
	if (accountNumber <= 0)
		cout << "Invalid account number entered.\n";
	else
		this->accountNumber = accountNumber;

	// Invoke member function to set balance
	setBalance(balance);
}