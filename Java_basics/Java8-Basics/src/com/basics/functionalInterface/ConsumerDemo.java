package com.basics.functionalInterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerDemo  {

	public static void main(String[] args) {
//	  Consumer<Integer> consumer=(t) -> System.out.println("printing : " + t);	
//			consumer.accept(4000);
//			
			
			List<Integer> numberList = Arrays.asList(1,2,3,4,5);
			
			numberList.stream().forEach( number-> System.out.println("printing: " + number));
	}
	

}
