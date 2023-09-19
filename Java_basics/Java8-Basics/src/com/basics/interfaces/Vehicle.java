package com.basics.interfaces;

// using the interface by third user 
public class Vehicle {

	public static void main(String[] args) {
		Drivable d1 = new Car();
		Drivable d2 = new Bike();
		Drivable d3 = new Truck();
		d1.driven();
		d2.driven();
		d3.driven();

	}
}

// implementation done by second user 
class Car implements Drivable {

	@Override
	public void driven() {
		System.out.println("the car can be driven");

	}

}

class Truck implements Drivable {

	@Override
	public void driven() {
		System.out.println("the Truck can be driven");

	}

}

class Bike implements Drivable {

	@Override
	public void driven() {
		System.out.println("the Bike can be driven");

	}

}
