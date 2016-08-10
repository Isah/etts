package com.data.dataFacade;

import com.application.timeplans.AnnualLeave;
import com.application.timeplans.Leave;
import com.application.timeplans.SickLeave;
import com.data.profiledatamgt.SignUpDAO;
import com.data.worktimedatamgt.AnnualLeaveDAO;
import com.data.worktimedatamgt.SickLeaveDAO;
import com.data.worktimedatamgt.SuperLeavePlanDAO;
import com.data.worktimedatamgt.TimeSheetDAO;

public class LeaveDataFacade {

	

	AbstractDAOFactory factory = null;
	SickLeaveDAO sickleavedao = null;
    AnnualLeaveDAO annualleavedao  = null;
	
	public LeaveDataFacade(){
		
		factory = AbstractDAOFactory.getDatabaseFactory(AbstractDAOFactory.GAE_MYSQL);
	    
	   
	}
	
	
	public void doe(){
		
     
		
	}
	
	
	public boolean approveLeave(String leaveType, String leaveno, String employee_no, int state){
		
		boolean isApproved = false;
		
		if(leaveType.equals("sick")){
			
		isApproved = sickleavedao.approveLeave(leaveno, employee_no, state);	
			
		}
		else if(leaveType.equals("sick")){
			
		isApproved = annualleavedao.approveLeave(leaveno, employee_no, state);	
			
		}
		
		return isApproved;
		
	}
	
	public boolean submitLeave(Leave leave,String leavetype){
		
		boolean isSubmitted = false;
		
		if(leavetype.equals("sickleave")){
			SickLeave sleave = (SickLeave) leave;
			
		sickleavedao = factory.getSickLeaveDAO();
		isSubmitted	= sickleavedao.insertSickLeave(sleave);
		}
		
		else if(leavetype.equals("annualleave")){
			AnnualLeave aleave = (AnnualLeave) leave;
			
		annualleavedao = factory.getAnnualLeaveDAO();
		isSubmitted = annualleavedao.insertAnnualLeave(aleave);	
			
			
		}
		
		
		return isSubmitted;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
