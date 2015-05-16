// Implementation file

#include "Vehicle.h"
using namespace std;

/*** Vehicle class implementations ***/

// Constructor & destructor
Vehicle::Vehicle(float w) { weight = w; }
Vehicle::~Vehicle() {};


/*** Truck class implementations ***/

// Constructor & destructor
Truck::Truck(float w) : Vehicle(w) { cout << "A truck of weight " << this->weight << " has been created.\n"; }
Truck::~Truck() {}

// Displays truck's information
void Truck::print() const {
	cout << "The truck weighs " << this->weight << ".\n";
}


/*** Car class implementations ***/

// Constructor & destructor
Car::Car(float w) : Vehicle(w) { cout << "A car of weight " << this->weight << " has been created.\n"; }
Car::~Car() {}

// Displays truck's information
void Car::print() const {
	cout << "The car weighs " << this->weight << ".\n";
}


/*** Engine class implementations ***/

// Constructor & destructor
Engine::Engine() {}
Engine::~Engine() {}

void Engine::start() const { cout << "Engine started.\n"; }
void Engine::stop() const { cout << "Engine stopped.\n"; }


/*** Tire class implementations ***/

// Constructor & destructor
Tire::Tire(int p) { pressure = p; }
Tire::~Tire() {};

// Change tire pressure
int Tire::inflate(int psi) {
	pressure = pressure + psi;
	cout << "Pressure has been inflated by " << psi << "psi. ";
	return pressure;
}


/*** Window class implementations ***/

// Constructor & destructor
Window::Window() {}
Window::~Window() {};

// Prints information about status of window
void Window::rollup() const { cout << "Window is up.\n"; }
void Window::rolldown() const { cout << "Window is down.\n"; }


/*** Door class implementations ***/

// Constructor & destructor
Door::Door() {}
Door::~Door() {};

// Prints information about status of door
void Door::open() const { cout << "Door is open.\n"; }
void Door::close() const { cout << "Door is closed.\n"; }