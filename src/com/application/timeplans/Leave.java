package com.application.timeplans;

public class Leave {
	
	//private String name_of_staff;
	private String leave_no;
	private String employeeno;
	private String department;
	private String date;
	private String startDate;
	private String endDate;
	private int noOfDaysRequested;
	//private int noOfDaysEntitled;
	private String leaveType;
	

	public Leave(String employeeno, String departemnt, String date, String startDate,String endDate, int daysRequested, String leaveType,String leave_no){
		this.employeeno = employeeno;
		this.department = departemnt;
		this.date = date;
		this.startDate = startDate;
		this.endDate = endDate;
		this.noOfDaysRequested = daysRequested;
		//this.noOfDaysEntitled = daysEntitled;
		this.leaveType = leaveType;
	}
	
	
	
	
	
	public String getLeave_no() {
		return leave_no;
	}





	public void setLeave_no(String leave_no) {
		this.leave_no = leave_no;
	}





	public String getLeaveType() {
		return leaveType;
	}





	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}



	public int getNoOfDaysRequested() {
		return noOfDaysRequested;
	}


	public void setNoOfDaysRequested(int noOfDaysRequested) {
		this.noOfDaysRequested = noOfDaysRequested;
	}


	public String getEmployeeno() {
		return employeeno;
	}


	public void setEmployeeno(String employeeno) {
		this.employeeno = employeeno;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	

	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
