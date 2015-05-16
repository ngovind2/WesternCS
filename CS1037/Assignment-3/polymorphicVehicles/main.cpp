// Manipulates different vehicle objects

#include <iostream>
#include "Vehicle.h"

using namespace std;

int main() {

    int pressure;                         
    
	Truck truck(30000);                     // Create a truck object with weight of 30,000
	Car car;                                // Create a car object with default weight
	
    pressure = car.tire[0].inflate(10);		// Inflate car's first tire by 10 psi
    cout << "The car's pressure is now" << pressure << ".\n";
    
	car.right.close();                      // Close the car's right door
	car.left.window.rollup();               // Roll up the car's left window
	car.engine.start();                     // Start the car's engine
    
	return 0;
}
