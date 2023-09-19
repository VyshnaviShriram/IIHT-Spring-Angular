package com.basics.dateandtime;
import java.time.LocalDate;
import java.time.LocalDateTime; 
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;

public class DateTime {
	
	public static void main(String[] args) {
		
		// we dont need new because we dont want to create a new object 
		LocalDate today = LocalDate.of(2022,Month.AUGUST, 15);
		System.out.println(today);
		
		LocalDateTime rightNow = LocalDateTime.now();
		System.out.println(rightNow);
//		Date today = new Date();
		
		LocalTime t1 = LocalTime.now(ZoneId.of("CET"));
		System.out.println(t1);
	

} }
