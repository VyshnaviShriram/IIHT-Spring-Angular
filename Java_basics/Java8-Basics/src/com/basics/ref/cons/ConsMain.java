package com.basics.ref.cons;

public class ConsMain {
	public static void main(String[] args) {
		
		System.out.println("we are studeing cont ref");
	
		IStudent iStudent= () -> new Student();
		
		
		Student student = iStudent.getStudent();
		student.display();
	}

}
