package com.data.worktimedatamgt;

import java.sql.SQLException;
import java.util.List;

import com.application.scheduler.Schedule;
import com.application.timeplans.Leave;
import com.data.dataFacade.DatabaseConfig;
import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.PreparedStatement;

public abstract class SuperLeavePlanDAO {

	
        protected boolean insertSuperLeave(Leave leave){
		
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
    
      public boolean approveLeave(String leaveno, String employee_no, int state) {
    	  Connection  conn = DatabaseConfig.getMySqlInstance().connect();
    	 Schedule sch = new Schedule();
  		String time = sch.getCurrentDate();
  		
  		// TODO Auto-generated method stub
  		int isUpdate = 0;
  		boolean isApproved = false;
  		String approveSql = "insert into leave_approval (patner_id,plan_id,date,approval_state) values ((select patner_id from patner where person_in_mgt_id = (select person_in_mgt_id from person_in_mgt where employee_id = (select employee_id from employee where employee_no = '"+employee_no+"')) ), (select plan_id from leaveplan where leave_no = '"+leaveno+"') ,'"+time+"' ,'"+state+"'  )";
  		//String approveSql = "insert into leave_approval (patner_id,plan_id,date,approval_state) values ((select patner_id from patner where person_in_mgt_id = (select person_in_mgt_id from person_in_mgt where employee_id = (select employee_id from employee where employee_no = '"+employee_no+"')) ))";
  		try {
  			PreparedStatement pstatement = conn.prepareStatement(approveSql);
  			isUpdate = pstatement.executeUpdate();
  			
  			if(isUpdate >0){		
  				isApproved = true;
  				
  			}
  			else{
  				isApproved = false;
  				
  			}
  			
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  		
  		return isApproved;
  	}
	
	
    
	public  List<Leave> getAllLeavePlans(String startDate, String endDate){
		
		
		return null;
	}


	
	protected String getEmployee_No(int eid){
		Connection  conn = DatabaseConfig.getMySqlInstance().connect();
        
		String q = "select employee_no from employee where employee_id = '"+eid+"'";
		int eID = DatabaseConfig.getMySqlInstance().getResultSetInt(q, conn);
		
		return Integer.toString(eID);
		
	}
	
	
	
	
}
  