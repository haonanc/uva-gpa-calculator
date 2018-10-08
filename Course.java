import java.util.Arrays;

public class Course {
	private double hours;
	private String name;
	private String grade;
	private String status;
	public static final String[] gradeScale ={"Blank","A+","A","A-","B+","B","B-","C+","C","C-","D+","D","D-","F"};
	public static final double[] gpaScale = {0 ,4.0,4.0 ,3.7 ,3.3 ,3.0, 2.7, 2.3, 2.0, 1.7, 1.3, 1.0, 0.7, 0.0};
	
	public Course() {
		this.hours = 3;
		this.grade = "Blank";
		this.name = "N/A";
		this.status = "N/A";
		
	}
	
	public Course(double hours, String grade, String name, String status) {
		this.hours = hours;
		this.grade = grade;
		this.name = name;
		this.status = status;
	}
	
	public double getHours() {
		return this.hours;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getGrade() {
		return this.grade;
	}
	
	public double getGradeInInt() {
		int index = Arrays.asList(gradeScale).indexOf(grade);
		return gpaScale[index];
		
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public String toString() {
		return "Name:" + name + " | Credits:" + String.valueOf(hours);
	}
	
	public String fullName() {
		return "Name:" + name + " | Credits:" + String.valueOf(hours) + "         | Grade:" + grade+ " | Status:" + status;
	}



}
