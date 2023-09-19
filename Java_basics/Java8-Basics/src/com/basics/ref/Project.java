package com.basics.ref;

import java.time.LocalDate;

public class Project {
	
	public static void projectProgress() {
		
System.out.println("Project is making progress");
System.out.println("project status was approved by client");
LocalDate now = LocalDate.now();
System.out.println("on " + now.toString() );
	}
	
	public static void threadTask() {
		for (int i = 1; i <=10 ; i++) {
			System.out.println(i*2);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} // create method to print odd numbers till 100
			// method oddto100
	}

}
