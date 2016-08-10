package com.data.dataFacade;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.application.scheduler.Schedule;
import com.application.staff.Employee;
import com.application.timeplans.DayPlan;
import com.application.timeplans.TimeSheet;
import com.data.profiledatamgt.SignUpDAO;
import com.data.worktimedatamgt.TimeSheetDAO;

public class TimeSheetDataFacade {

	
	AbstractDAOFactory factory = null;
	TimeSheetDAO timesheetdao = null;
	SignUpDAO signupdao = null;
	Schedule schedule = null;
	
	public TimeSheetDataFacade(){
		
		factory = AbstractDAOFactory.getDatabaseFactory(AbstractDAOFactory.GAE_MYSQL);
	    timesheetdao = factory.getTimeSheetDAO();	
	    signupdao = factory.getSignUpDAO();
	    schedule = new Schedule();
	    
	}
	
	public boolean addDayPlan(DayPlan plan){
	return timesheetdao.insertDayPlan(plan);
		
		
	 }
	
	
	public int getEID(String user,String pass){
		
		return signupdao.getEmpID(user, pass);
		
		
	}
	
	 public List<TimeSheet> searchTimeSheet(String dateFro, String employee, String searchby,String item,int approvestate){
		
		return timesheetdao.lookupTimeSheet(dateFro,employee,searchby,item,approvestate); 
			
		}
	
	
	public boolean submitTimeSheet(TimeSheet timesheet){
		
	return timesheetdao.insertTimeSheet(timesheet);
		
	}
	
	public boolean submitDayPlan(DayPlan dayplan){
		
		return timesheetdao.insertDayPlan(dayplan);
		
	}
	
    public boolean approveTimeSheet(int timesheetID, int approvalstate, int employee_no){
    //employee_no - is for the person who approved		
		
    String	sqlDate	= schedule.getCurrentDate();

    return timesheetdao.recordApproval(timesheetID, approvalstate, employee_no,sqlDate);
		
	}
	
	
	public List<DayPlan> getDayPlans(String employeeno){
		
		return timesheetdao.getAllDayPlans(employeeno);
		
	}
	
	public List<TimeSheet> getApprovedTSheets(String d1, String d2, String filter, String employeeno){
		
		return timesheetdao.getApprovedTSheets(d1, d2, filter, employeeno);
		
	}
	
	
     public List<TimeSheet> getPendingTSheets(String d1, String d2, String filter,String employeeno){
		
		return timesheetdao.getPendingTSheets(d1, d2, filter,employeeno);
		
	}
	
     
     public TimeSheet getTimeSheet(String timesheetid){
    	 
    	return timesheetdao.getTimeSheet(timesheetid);
    	 
     }
	
	
	public boolean checkTimeSheetSubmitted(String timeSheetName){
		
		
		return timesheetdao.isTimeSheetSubmitted(timeSheetName);
		
		
	}
	
	public Employee getEmployee(String id){
		
		
		return timesheetdao.getEmployee(id);
	}
	
	
	 public int get_EID_From_PIN_MGT(int id){
		
		return timesheetdao.getEmployeeIDFromPersonInMgt(id);
		
		
	  }
	
	
	  public boolean isUsernameTaken(String username){
	    	 
	    	 
	    	 return signupdao.isUserNameTaken(username);
	  }
	     
	  public boolean isEmailExist(String email){
	    	
	    	return signupdao.isEmailExist(email);
	    	
	  }
	  
	  
	  public boolean isEmployeeNoExist(String employeeno){
		  
		    return  signupdao.isEmployeeNoExist(employeeno);
		  
	  }
	
	  
	  public List<Employee> getAllEmployees(){
		  
		  return timesheetdao.getAllEmployees();
		  
	  }
	
	
	
}
