package com.learnmockito.LearnMockito;

public class Calculator  {
	
	CalculatorService service;
	
	public Calculator(CalculatorService service) 
	{
		this.service = service;
	}
	
//	public int add(int num1, int num2 ) {
//		return num1 + num2;
//	}
//	
//	public int sub(int num1, int num2 ) {
//		return num1 - num2;
//	}
//	
//	
//	public int multiply(int num1, int num2 ) {
//		return num1 * num2;
//	}

	public int perform(int i, int j) {
		// TODO Auto-generated method stub
		return service.add(i,j); 
	}
	
	public int subperform(int i, int j) {
		// TODO Auto-generated method stub
		return service.sub(i,j); 
	}
	public int mulperform(int i, int j) {
		// TODO Auto-generated method stub
		return service.mul(i,j); 
	}
	

}