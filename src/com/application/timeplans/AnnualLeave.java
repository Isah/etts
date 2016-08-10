package com.application.timeplans;

public class AnnualLeave extends Leave{

	
	private int year_of_theleave;
	
	public AnnualLeave(String employeeno, String department, String date,String startDate,String endDate,int daysRequested, String leaveType,String leave_no){
    	super(employeeno,department, date,startDate,endDate,daysRequested,leaveType,leave_no);
	}
	
	
	
	public int getYear_of_theleave() {
		return year_of_theleave;
	}
	public void setYear_of_theleave(int year_of_theleave) {
		this.year_of_theleave = year_of_theleave;
	}
	
	

	
	
	
}
