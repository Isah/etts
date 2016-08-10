package com.data.dataFacade;

import com.data.profiledatamgt.SignUpDAO;
import com.data.worktimedatamgt.AnnualLeaveDAO;
import com.data.worktimedatamgt.SickLeaveDAO;
import com.data.worktimedatamgt.SuperLeavePlanDAO;
import com.data.worktimedatamgt.TimeSheetDAO;

public abstract class AbstractDAOFactory {

	
	 public static final int GAE_MYSQL = 0;
	    // public static final int ORACLE = 1;
	    //The factories as a family
	    
	    public abstract TimeSheetDAO getTimeSheetDAO();   //product interface
	    public abstract SignUpDAO getSignUpDAO();    //
	    public abstract SickLeaveDAO getSickLeaveDAO();
	    public abstract AnnualLeaveDAO getAnnualLeaveDAO();
	    
	    
	    
	    static AbstractDAOFactory thisFactory;
	    
	    public static AbstractDAOFactory getDatabaseFactory(int factory){
	    
	    if(factory == 0){
	    thisFactory = new GAE_MysqlFactory();
	    
	    }
	    else if(factory == 1){
	    
	    //not yet implemented 
	    }
	    return thisFactory;
	    
	    }
	
	
	
	
	
	
	
}
