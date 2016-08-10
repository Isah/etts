package com.data.worktimedatamgt;

import com.application.timeplans.AnnualLeave;
import com.application.timeplans.Leave;
import com.application.timeplans.SickLeave;

public interface SickLeaveDAO {

	
	
	public boolean insertSickLeave(SickLeave leave);
	public SickLeave getLeave(String leaveno); 
	public boolean approveLeave(String leaveno, String employee_no, int state);
	
}
