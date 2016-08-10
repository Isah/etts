package com.application.timeplans;

import java.sql.Date;
import java.util.List;

import com.application.staff.Employee;

public class TimeSheet {

	private int timesheet_id;
	private List<DayPlan> dayplans;
	private String dateofcreation;
	private String timeSheetName;
    private int employee_id;
    private Approval approval;
  


	public Approval getApproval() {
		return approval;
	}


	public void setApproval(Approval approval) {
		this.approval = approval;
	}


	public int getEmployee_id() {
		return employee_id;
	}
	
	
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	
	public String getTimeSheetName() {
		return timeSheetName;
	}
	
	public void setTimeSheetName(String timeSheetName) {
		this.timeSheetName = timeSheetName;
	}
	
	
	public String getDateofcreation() {
		return dateofcreation;
	}
	public void setDateofcreation(String dateofcreation) {
		this.dateofcreation = dateofcreation;
	}
	
	
	public int getTimesheet_id() {
		return timesheet_id;
	}
	public void setTimesheet_id(int timesheet_id) {
		this.timesheet_id = timesheet_id;
	}



	public List<DayPlan> getDayplans() {
		return dayplans;
	}
	public void setDayplans(List<DayPlan> dayplans) {
		this.dayplans = dayplans;
		
	}
	
	
	
	
	
	
	
	
	
	
}
