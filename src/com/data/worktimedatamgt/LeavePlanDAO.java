package com.data.worktimedatamgt;

import java.sql.SQLException;
import java.util.List;

import com.application.timeplans.Leave;
import com.data.dataFacade.DatabaseConfig;
import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.PreparedStatement;

public abstract class LeavePlanDAO {

	
      public boolean insertSuperLeave(Leave leave){
		
    	Connection  conn = DatabaseConfig.getMySqlInstance().connect();

		
		String department = leave.getDepartment();
		String dateOfRequest = leave.getDate();
		String startDate = leave.getStartDate();  
		String endDate = leave.getEndDate();
		//int daysEntitled = leave.getNoOfDaysEntitled();
		int daysRequested = leave.getNoOfDaysRequested();
		
		System.out.println("depart"+department);
		System.out.println("date"+dateOfRequest);
		System.out.println("depart"+ startDate);
		System.out.println("depart"+department);
		System.out.println("depart"+endDate);
		
        String sql = "insert into leaveplan (employee_id,department,request_date,startdate,endDate) values ((select employee_id from employee where employee_no = '"+leave.getEmployeeno()+"'), '"+department+"','"+dateOfRequest+"','"+startDate+"','"+endDate+"') ";
     
      
		try {
		    PreparedStatement pst = conn.prepareStatement(sql);
			pst.execute();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	   }
    
	
	
	public abstract boolean insertLeave(Leave leave);
    
	public abstract List<Leave> getAllLeavePlans(String startDate, String endDate);
	
	public abstract Leave getLeave(String leaveno);
	
	public abstract boolean approveLeave(String leaveno, String patner_employeeno);
	
	
	
	
	
	
	
	
}
  