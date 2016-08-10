package com.data.worktimedatamgt;

import com.application.timeplans.AnnualLeave;
import com.application.timeplans.Leave;

public interface AnnualLeaveDAO {

	

	public boolean insertAnnualLeave(AnnualLeave leave);
	public AnnualLeave getLeave(String leaveno); 
	public boolean approveLeave(String leaveno, String employee_no, int state);
	
	
	
}
