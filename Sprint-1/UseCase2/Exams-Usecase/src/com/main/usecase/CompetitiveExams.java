package com.main.usecase;

public class CompetitiveExams {
	private int courseId;
	private String courseName;
	private String courseTeacher;
	private int courseDuration;
	private int totalFee;
	
	public CompetitiveExams(int courseId, String courseName, String courseTeacher, int courseDuration,
			int totalFee) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseTeacher = courseTeacher;
		this.courseDuration = courseDuration;
		this.totalFee = totalFee;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return "CompetitiveExams [courseId=" + courseId + ", courseName=" + courseName + ", courseTeacher="
				+ courseTeacher + ", courseDuration=" + courseDuration + ", totalFee=" + totalFee + "]";
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseTeacher() {
		return courseTeacher;
	}

	public void setCourseTeacher(String courseTeacher) {
		this.courseTeacher = courseTeacher;
	}

	public int getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(int courseDuration) {
		this.courseDuration = courseDuration;
	}

	public int getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(int totalFee) {
		this.totalFee = totalFee;
	}
	
//	CompetitiveExams exam1=new CompetitiveExams(1, "JEE Mains", "John", 12, 10000);
//	CompetitiveExams exam2=new CompetitiveExams(2, "CAT", "Thomas", 18, 50000);
//	CompetitiveExams exam3=new CompetitiveExams(3, "GATE", "Ram", 11, 60000);
//	CompetitiveExams exam4=new CompetitiveExams(4, "NEET", "Akhil", 19, 100000);
//	CompetitiveExams exam5=new CompetitiveExams(5, "EAMCET", "Anusha", 6, 2000);
//	CompetitiveExams exam6=new CompetitiveExams(6, "UPSC", "Amala", 24, 70000);
//	CompetitiveExams exam7=new CompetitiveExams(7, "SSC CGL", "Jaanu", 5, 5000);
//	CompetitiveExams exam8=new CompetitiveExams(8, "KCET", "Jahnavi", 2, 300000);
//	CompetitiveExams exam9=new CompetitiveExams(9, "IBPS PO", "Neha", 10, 45000);
//	CompetitiveExams exam10=new CompetitiveExams(10, "CTET", "Madhuri", 9, 120000);
//	CompetitiveExams exam11=new CompetitiveExams(11, "ISRO", "Arjun", 22, 70000);
//	CompetitiveExams exam12=new CompetitiveExams(12, "DRDO", "Aishwarya", 30, 60000);
//	CompetitiveExams exam13=new CompetitiveExams(13, "BITSAT", "Sai", 4, 3000);
//	CompetitiveExams exam14=new CompetitiveExams(14, "SSC CHSL", "Vani", 8, 4500);
//	CompetitiveExams exam15=new CompetitiveExams(15, "JEE Advanced", "Lakshman", 15, 7000);

}
