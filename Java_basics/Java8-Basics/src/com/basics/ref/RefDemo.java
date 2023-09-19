package com.basics.ref;

public class RefDemo {

	public static void main(String[] args) {
		System.out.println("Method reference demo");
		
		// provide the implementation of IWork
		//classname::methodname
	IWork iWork = Project::projectProgress;
		iWork.doProject();
		
		
	Runnable runnable = Project::threadTask;
	Thread t1 = new Thread(runnable);
	t1.start();
	}
	
	// create a method ref to that method in the project class

}
