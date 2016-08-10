package com.application.timeplans;

public class ExamLeave extends Leave{

    private String course;
	private String papers;


	
	public ExamLeave(String employeeno, String department, String date,String startDate,String endDate,int daysRequested,String leaveType,String leave_no){
    	super(employeeno,department, date,startDate,endDate,daysRequested,leaveType,leave_no);
	}



	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
	

	public String getPapers() {
		return papers;
	}

	public void setPapers(String papers) {
		this.papers = papers;
	}

	
	
	
	
	
	
}
