package com.data.worktimedatamgt;

import java.util.List;

import com.application.staff.Employee;
import com.application.timeplans.DayPlan;
import com.application.timeplans.TimeSheet;

public interface TimeSheetDAO {

	    public boolean insertTimeSheet(TimeSheet timesheet);
	    public boolean insertDayPlan(DayPlan plan);
	    public boolean recordApproval(int timesheetID, int approvalstate,int employee_no, String date);
	    
	    public boolean updateTimeSheet(String timesheetname);
	    public boolean isTimeSheetSubmitted(String timeSheetName);
	    
	    public boolean deleteTimeSheet(int timesheet_id);
	    
	    
	    public List<DayPlan> getAllDayPlans(String employeeno);
	    public List<TimeSheet> lookupTimeSheet(String dateFro, String employee,String searchby,String item,int approvestate);
	    public Employee getEmployee(String employee_no); //for the mean time
	    public List<Employee> getAllEmployees();
	    public List<TimeSheet> getApprovedTSheets(String dateFro, String dateTo, String filter, String employeeno);
	    public List<TimeSheet> getPendingTSheets(String dateFro, String dateTo, String filter, String employeeno);
	    
	    public TimeSheet getTimeSheet(String timesheetid);
	    
	   
	    public int getNoOfApprovedTSheets();
	    public int getNoOfPendingTSheets();
	    public int getNoOfRejectedTSheets();
	    
	    public int getEmployeeIDFromPersonInMgt(int id);
	    
	    
	    
	    
	    
}
