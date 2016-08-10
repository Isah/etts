package com.data.profiledatamgt;

import com.application.staff.Employee;

public interface SignUpDAO {

	
	
	   public int getEmpID(String username,String pass);
	   public boolean isUserValid(String username,String pass);
	   
	   public boolean registerUser(Employee employee,String user);
	   
	   public Employee getEmployee(String employeeno);
	   
	   public boolean isUserNameTaken(String username);
	   public boolean isEmailExist(String email);
	   
	   public boolean isEmployeeNoExist(String employeeno);
	   
	
}
