// Stock class function implementation file

#include "Stock.h"

/*************************************************************
*                    Stock::setItemNumber                    *
* Argument is copied into the member variable 'itemNumber'   *
*************************************************************/
void Stock::setItemNumber(int i) {
	itemNumber = i;
}

/*************************************************************
*                    Stock::setQuantity                      *
* Argument is copied into the member variable 'quantity'     *
*************************************************************/
void Stock::setQuantity(int q) {
	quantity = q;
}

/*************************************************************
*                    Stock::setCost                          *
* Argument is copied into the member variable 'cost'         *
*************************************************************/
void Stock::setCost(double c) {
	cost = c;
}

/*************************************************************
*                     Stock::getItemNumber                   *
* Returns the value of member variable 'itemNumber'          *
*************************************************************/
int Stock::getItemNumber() {
	return itemNumber;
}

/*************************************************************
*                     Stock::getQuantity                     *
* Returns the value in member variable 'quantity'            *
*************************************************************/
int Stock::getQuantity() {
	return quantity;
}

/*************************************************************
*                     Stock::getCost                         *
* Returns the value in member variable 'cost'                *
*************************************************************/
double Stock::getCost() {
	return cost;
}

/*************************************************************
*                     Stock::getTotalCost                    *
* Returns the total cost of purchasing all items             *
*************************************************************/
double Stock::getTotalCost() {
	return (quantity * cost);
}

/******************************
*  Stock default constructor  *
******************************/
Stock::Stock() {
	itemNumber = 0;
	quantity = 0;
	cost = 0.00;
}

/********************************
*  Stock overloaded constructor *
********************************/

Stock::Stock(int i, int q, double c) {
	setItemNumber(i);
	setQuantity(q);
	setCost(c);
}

/*********************
*  Stock destructor  *
*********************/
Stock::~Stock() {}			
