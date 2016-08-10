package com.data.worktimedatamgt;

import com.application.timeplans.ExamLeave;
import com.application.timeplans.SickLeave;

public interface ExamLeaveDAO {
	
	
	
	public boolean insertExamLeave(SickLeave leave);
	public ExamLeave getLeave(String leaveno); 
	public boolean approveLeave(String leaveno, String employee_no, int state);
        
        

}
