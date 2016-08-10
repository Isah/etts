package com.data.dataFacade;

import com.application.staff.Employee;
import com.data.profiledatamgt.SignUpDAO;
import com.data.worktimedatamgt.TimeSheetDAO;

public class ProfileManager {

	AbstractDAOFactory factory = null;

	SignUpDAO signupdao = null;
	
	
	
	public ProfileManager() {
		super();
		factory = AbstractDAOFactory.getDatabaseFactory(AbstractDAOFactory.GAE_MYSQL);
	 	
	    signupdao = factory.getSignUpDAO();
		
		
	}
	
	
	public boolean checkUserValid(String user,String pass){
		
		
		return signupdao.isUserValid(user, pass);
		
		
		
	}
	
	
	public boolean signUp(Employee employee, String user){
		
		
		return signupdao.registerUser(employee, user);
		
		
	}
	
	
	
	
	
	
	
}
