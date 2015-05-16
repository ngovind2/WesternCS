// Stock class specification file

#ifndef STOCK_H
#define STOCK_H

// Stock class declaration
class Stock {

private:
	int itemNumber;
	int quantity;
	double cost;

public:
	Stock();
	Stock(int, int, double);
	~Stock();

	void setItemNumber(int);
	void setQuantity(int);
	void setCost(double);

	int getItemNumber();
	int getQuantity();
	double getCost();
	double getTotalCost();
};
#endif
