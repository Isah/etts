package com.data.worktimedatamgt;

import java.sql.SQLException;
import java.util.List;

import com.application.timeplans.AnnualLeave;
import com.application.timeplans.Leave;
import com.application.timeplans.SickLeave;
import com.data.dataFacade.DatabaseConfig;
import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.PreparedStatement;
import com.google.cloud.sql.jdbc.ResultSet;
import com.google.cloud.sql.jdbc.Statement;

public class AnnualLeaveImpl extends SuperLeavePlanDAO implements AnnualLeaveDAO{

	
	Connection conn =null;
	
	public AnnualLeaveImpl() {
		super();
		

		 conn = DatabaseConfig.getMySqlInstance().connect();
		
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean insertAnnualLeave(AnnualLeave leave) {
		// TODO Auto-generated method stub

		super.insertSuperLeave(leave);
		
        String leavetypesql = "";
  
 	  

 	    int yearOfleave = leave.getYear_of_theleave();
 	   
 
 	   leavetypesql = "insert into leave_annual (plan_id, year_of_theleave) values ((select max(plan_id) from leaveplan),'"+yearOfleave+"')";
	
   
		try {
			
			PreparedStatement pst2 = conn.prepareStatement(leavetypesql);
			pst2.execute();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getSQLState());
			System.out.println(e.getMessage());
		}
		

		return false;
		
		
		
	}


	
	
	
	
	@Override
	public AnnualLeave getLeave(String leaveno) {
		
		AnnualLeave leave = null;
			
			try {
				
				String query = "select a.year_of_theleave, l.employee_id, l.department,l.request_date,l.startDate,l.endDate,l.no_days_requested,l.leaveType from leave_annual as a inner join leaveplan as l on a.plan_id = l.plan_id  where l.leave_no = '"+leaveno+"'";
				Statement leaveStatement = conn.createStatement();
			    ResultSet results = leaveStatement.executeQuery(query);	
				
			    while(results.next()){
			    	String leave_no = results.getString("leave_no");
			        String employeeno = getEmployee_No(Integer.parseInt(results.getString("employee_id")));
			    	String department = results.getString("department");;
			    	String date = results.getString("request_date");
			    	String startDate = results.getString("startDate");
			    	String endDate = results.getString("endDate");;
			    	int noOfDaysRequested = results.getInt("no_days_requested");;
			    	//int noOfDaysEntitled = results.getInt("leave_no");;
			    	String leaveType = results.getString("leaveType");
			    	int year_of_theleave = results.getInt("year_of_theleave");
			    	
			    	
			    	leave = new AnnualLeave(employeeno,department, date,startDate,endDate,noOfDaysRequested,leaveType,leave_no);
			    	leave.setYear_of_theleave(year_of_theleave);
			    	
	
			    }
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return leave;
	}
	
	
	
	
	
	
	

}
