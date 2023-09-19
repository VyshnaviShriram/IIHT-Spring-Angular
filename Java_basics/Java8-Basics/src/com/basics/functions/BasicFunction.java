package com.basics.functions;

public class BasicFunction {
	
	public static void main(String[] args) {
		System.out.println( Addition(12,23));
		System.out.println("Sub " + Sub(100, 10));
		System.out.println( "multiply " + Multiplication(10, 10));
		System.out.println( "Square " + Square(90));
		System.out.println("Cube " + cube(40));
	}
	
	static Integer Addition(Integer num1, Integer num2) {
		return num1 + num2;
	} // sub/ multiplication , division, square , cube 
	
	
	static Integer Multiplication(Integer num1 , Integer num2) {
		return num1 * num2;
	}
	
	static Integer Sub(Integer num1 , Integer num2) {
		return num1 - num2;
	}
	static Integer Div(Integer num1 , Integer num2) {
		return num1 / num2;
	}
	static Integer Square(Integer num1) {
		return num1 * num1;
	}
	
	static Integer cube(Integer num1) {
		return num1 * num1 * num1;
	}
}
