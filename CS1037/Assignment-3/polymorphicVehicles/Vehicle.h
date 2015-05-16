// Header file

#ifndef VEHICLE_H
#define VEHICLE_H

#include <iostream>

class Vehicle {

public:
	float weight;                     // Member variable
	virtual void print() const = 0;   // Abstract function (denotes an abstract class)

	// Constructor & destructor
	Vehicle(float w = 0.0);
	~Vehicle();
};

class Truck : public Vehicle {

public:

	// Member variables & functions
	void print() const;               // Concrete defintion of print function

	// Constructor & Destructor
	Truck(float w = 0.0);
	~Truck();
};

class Engine {

public:

	// Member functions
	void start() const;
	void stop() const;

	// Constructor & Destructor
	Engine();
	~Engine();
};

class Tire {

public:

	// Member variables & functions
	int pressure;
	int inflate(int psi);

	// Constructor & Destructor
	Tire(int p = 30);
	~Tire();

};

class Window {

public:

	// Member functions
	void rollup() const;
	void rolldown() const;

	// Constructor & Destructor
	Window();
	~Window();
};

class Door : public Window {

public:

	// Member variables & functions
	Window window;
	void open() const;
	void close() const;

	// Constructor & Destructor
	Door();
	~Door();
};

class Car : public Vehicle {

public:

	// Member variables
	Engine engine;
	Tire tire[4];
	Door left;
	Door right;
	void print() const;					      // Concrete defintion of print function

	// Constructor & Destructor
	Car(float w = 0.0);
	~Car();

};

#endif
