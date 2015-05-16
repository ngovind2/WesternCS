// Class specification (header) file for Account

#ifndef ACCOUNT_H
#define ACCOUNT_H

class Account {

private:
	int accountNumber; 	// Member variables
	double balance;

public:
	Account(int accountNumber, double balance = 0.0);	// Constructor
	int getAccountNumber() const;				
	double getBalance() const;
	void setBalance(double);
	void credit(double);
	void debit(double);
	void print() const;

};

#endif
