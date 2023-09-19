package com.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.*;
import org.junit.jupiter.api.Test;

public class JunitClass {
	String userName;

	@Before
	public void userName() {
		System.out.println("userName executed");
		userName= "Vyshnavi123";
	}
	
	@Test
	public void JuintMethod() {
		System.out.println("Test file executed");
	}

	@Test
	public void testUserMethood() {
		System.out.println("testUserMethood executed");
		System.out.println("userName is "+ userName);
		assertEquals("Vyshnavi123", userName);
	}
//	public static void main(String[] args) {
//		
//		JunitClass result= new JunitClass();
//		//result.testUserMethood();
//	}
}
