package com.data.worktimedatamgt;


import java.sql.SQLException;
import java.util.List;

import com.application.scheduler.Schedule;
import com.application.timeplans.AnnualLeave;
import com.application.timeplans.Leave;
import com.application.timeplans.SickLeave;
import com.data.dataFacade.DatabaseConfig;
import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.PreparedStatement;
import com.google.cloud.sql.jdbc.ResultSet;
import com.google.cloud.sql.jdbc.Statement;

public class SickLeavePlanImpl extends SuperLeavePlanDAO implements SickLeaveDAO{

	
	
    Connection conn = null;
	
	public SickLeavePlanImpl(){
		
		 conn = DatabaseConfig.getMySqlInstance().connect();

	}
	
	
	
	@Override
	public boolean insertSickLeave(SickLeave leave) {
	
		boolean finalisInserted = false;
		boolean isInserted1 = false;
		boolean isInserted2 = false;
		
		int countOfInsert =0;
		
		if(super.insertSuperLeave(leave)){
			
			isInserted1 = true; 
		}
		else{
			
		    isInserted1 = false;
		}
		
	
	
       String leavetypesql = "";

 	
 	   
 	   //String date = sl.getDate_of_returntoduty();
 	   String illnessdesc = leave.getDescription_illness();
 	   

 	   leavetypesql = "insert into leave_sick (plan_id, date_of_returntoduty,illness_desc) values ((select max(plan_id) from leaveplan),'"+illnessdesc+"')";
           
		try {
		
			PreparedStatement pst2 = conn.prepareStatement(leavetypesql);
			countOfInsert = pst2.executeUpdate();
			if(countOfInsert >0){
				
				isInserted2 = true;
				
			}
			else{
				
				isInserted2 = false;
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getSQLState());
			System.out.println(e.getMessage());
		}
		
		
		if(isInserted1 && isInserted2){
			
			finalisInserted =  true;
			
		}
		else {
			
			finalisInserted = false;
			
		}
		

		return finalisInserted;
	}


	
	
	public SickLeave getLeave(String leaveno) {
		// TODO Auto-generated method stub
		 SickLeave leave = null;
		
		try {
			
			String query = "select s.date_of_returntoduty,s.illness_desc, l.employee_id, l.department,l.request_date,l.startDate,l.endDate,l.no_days_requested,l.leaveType from leave_sick as s inner join leaveplan as l on s.plan_id = l.plan_id  where l.leave_no = '"+leaveno+"'";
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
		    	String illnesdesc = results.getString("illness_desc");
		    	
		    	
		    	leave = new SickLeave(employeeno,department, date,startDate,endDate,noOfDaysRequested,leaveType,leave_no);
		    	leave.setDescription_illness(illnesdesc); 
		    	
		    
		    	
		    	
		    }
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return leave;
	}
	





	@Override
	public List<Leave> getAllLeavePlans(String startDate, String endDate) {
		
		
		
		
		
		
		return null;
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	

}
