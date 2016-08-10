package com.application.worktimemgt;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.data.dataFacade.DatabaseConfig;
import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.ResultSet;

public class BeerExpert {
	
	
	
	Connection conn = null;
	
	
	public List<String> getBrands(String color){
		
		List<String> brands = new ArrayList<String>();
		
		if (color.equals("amber")) {
			
			brands.add("Jack Amber");
			brands.add("Red Moose"); 
		}
		else {
			
			String name ="";
			conn = DatabaseConfig.getMySqlInstance().connect();
			 
				  
	

			
			 String query = "select first_name from employee where employee_no = 500";
			 name = DatabaseConfig.getMySqlInstance().getName(query, conn, "last_name");
		    
			

			// name = DatabaseConfig.getMySqlInstance().getName(query, conn);
			System.out.println("The name: "+name);
			
			brands.add(name);
			brands.add("Gout Stout");
			
			
		
	}
		return brands;
	
	
	}
	

}
