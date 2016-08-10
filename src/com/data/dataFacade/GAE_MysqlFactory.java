package com.data.dataFacade;

import com.data.profiledatamgt.SignUpDAO;
import com.data.profiledatamgt.SignUpImpl;
import com.data.worktimedatamgt.AnnualLeaveDAO;
import com.data.worktimedatamgt.AnnualLeaveImpl;
import com.data.worktimedatamgt.SickLeaveDAO;
import com.data.worktimedatamgt.SuperLeavePlanDAO;
import com.data.worktimedatamgt.SickLeavePlanImpl;
import com.data.worktimedatamgt.TimeSheetDAO;
import com.data.worktimedatamgt.TimeSheetImpl;

public class GAE_MysqlFactory extends AbstractDAOFactory{

	@Override
	public TimeSheetDAO getTimeSheetDAO() {
		
		return new TimeSheetImpl();   
	}

	@Override
	public SignUpDAO getSignUpDAO() {
		
		return new SignUpImpl();
	}



	@Override
	public SickLeaveDAO getSickLeaveDAO() {
		// TODO Auto-generated method stub

		return  new SickLeavePlanImpl();
	}

	@Override
	public AnnualLeaveDAO getAnnualLeaveDAO() {
		// TODO Auto-generated method stub
		return new AnnualLeaveImpl();
	}

}
