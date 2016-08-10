package com.presentation.displaymgt;

import com.data.dataFacade.ProfileManager;

public class UserLoginValidation implements java.io.Serializable{
	private String username = "";
	private String password = "";
	private String message = " ";
	
			//delete
			String dbUsername = "quag";
			String dbPassword = "pass";
			//delete
	
	public UserLoginValidation(){
		//this.username = username;
		//this.password = password;
		
	}
	
	public String getMessage(){
		return message;
	}
	
	public String getUsername(){
		return username;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password= password;
	}
	
	public boolean validation(){
		
		ProfileManager manager = new ProfileManager();
		
		

		boolean validator = false;
		if(!manager.checkUserValid(username, password)){  
			message = "Invalid user";
			validator = false;
		}else if((manager.checkUserValid(username, password))){
			message = "";
			validator = true;
		}
		return validator;
	}
	
	public String check_userType(String username, String password){
		String user="pattern";
		//get user type from database to switch b'tn main pages pattern or other user
		
		return user;
	}

}
