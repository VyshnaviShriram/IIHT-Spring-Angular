package com.main.usecase;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ExamsSortingMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		
		List <CompetitiveExams> examList = Arrays.asList(new CompetitiveExams(1, "JEE Mains", "John", 12, 10000),
				 new CompetitiveExams(2, "CAT", "Thomas", 18, 50000),
				 new CompetitiveExams(3, "GATE", "Ram", 11, 60000),
				 new CompetitiveExams(4, "NEET", "Akhil", 19, 100000),
				 new CompetitiveExams(5, "EAMCET", "Anusha", 6, 2000),
				 new CompetitiveExams(6, "UPSC", "Amala", 24, 70000),
				 new CompetitiveExams(7, "SSC CGL", "Jaanu", 5, 5000),
				 new CompetitiveExams(8, "KCET", "Jahnavi", 2, 300000),
				 new CompetitiveExams(9, "IBPS PO", "Neha", 10, 45000),
				 new CompetitiveExams(10, "CTET", "Madhuri", 9, 120000),
				 new CompetitiveExams(11, "ISRO", "Arjun", 22, 75000),
				 new CompetitiveExams(12, "DRDO", "Aishwarya", 30, 60000),
				 new CompetitiveExams(13, "BITSAT", "Sai", 4, 3000),
				 new CompetitiveExams(14, "SSC CHSL", "Vani", 8, 4500),
				 new CompetitiveExams(15, "JEE Advanced", "Lakshman", 15, 7000));
		
		Comparator<CompetitiveExams> feeComparator = Comparator.comparing(CompetitiveExams::getTotalFee);
		Comparator<CompetitiveExams> durationComparator = Comparator.comparing(CompetitiveExams::getCourseDuration);
		List<CompetitiveExams> feeLowtoHigh = examList.stream().sorted(feeComparator).collect(Collectors.toList());
		List<CompetitiveExams> feeHightoLow = examList.stream().sorted(feeComparator.reversed()).collect(Collectors.toList());
		List<CompetitiveExams> durationLowtoHigh = examList.stream().sorted(durationComparator).collect(Collectors.toList());
		System.out.println("Price of course from Low to High is :"+feeLowtoHigh.toString());
		System.out.println("Price of course from High to Low is :"+feeHightoLow.toString());
		System.out.println("Duration of course from Low to High is :"+durationLowtoHigh.toString());
	}

}
