package com.application.performancemgt;

import java.util.List;

import com.application.timeplans.TimeSheet;
import com.data.dataFacade.AbstractDAOFactory;
import com.data.dataFacade.TimeSheetDataFacade;
import com.data.worktimedatamgt.TimeSheetDAO;

public class PerformanceAnalyzer {

	
	
	public int getTotalEmployeePeriodTime(int totalPeriodMon ,int totalPeriodTue ,int totalPeriodWed,int  totalPeriodThur,int  totalPeriodFri,int totalPeriodSat){
		return (totalPeriodMon + totalPeriodTue +totalPeriodWed+ totalPeriodThur+ totalPeriodFri+ totalPeriodSat);
		
		
	}
	
	
	public int getTotalWeekTime(int mon,int tue,int wed,int thur,int fri,int sat){
		return mon+tue+wed+thur+fri+sat;
		
	}
	
	
	
}
