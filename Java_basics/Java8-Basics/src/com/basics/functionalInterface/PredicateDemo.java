package com.basics.functionalInterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateDemo {

	public static void main(String[] args) {
		
		List<Integer> numberList = Arrays.asList(1,2,3,4,5);
		
		numberList.stream().filter((t) -> t%2 ==0).forEach( number-> System.out.println("printing: " + number));
	}
}
