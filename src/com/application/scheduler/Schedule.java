package com.application.scheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Schedule {
	
	
	
	public String getCurrentDate(){
		
		java.util.Date jdate = Calendar.getInstance().getTime();
   	    String DATE_TIME_FORMAT = "yyyy-MM-dd kk:mm:ss";
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        
      //  dateTimeFormat.setTimeZone(TimeZone.getTimeZone("UG"));
        
        
        String sqlDateofCreateion = dateTimeFormat.format(jdate);
       
        return sqlDateofCreateion;
	}
	
	
	public java.sql.Date getSqlDate(String date){
		
		
		return null;
	}
	
	
	public String convertToSqlDate(java.util.Date jdate){
		
		//java.util.Date jdate = Calendar.getInstance().getTime();
   	    String DATE_TIME_FORMAT = "yyyy-MM-dd";
   	    
   	   
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        
      //  dateTimeFormat.setTimeZone(TimeZone.getTimeZone("UG"));
        
        
        String sqlDate = dateTimeFormat.format(jdate);
       
		return sqlDate;
		
	}
	
	
public String convertToSqlDate_pString(String date){
	
	   String array[]  = date.split("-");
	   String month = array[0];
	   String day = array[1];
	   String year = array[2];
	
	   
	   
	
	   Calendar.getInstance().set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));  
	   Date javaDate = Calendar.getInstance().getTime();
	    
	   String DATE_TIME_FORMAT = "yyyy-MM-dd";
  	    
   	   
       SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
       
     //  dateTimeFormat.setTimeZone(TimeZone.getTimeZone("UG"));
       
       
       String sqlDate = dateTimeFormat.format(javaDate);
      
		return sqlDate;
		
		
	}
	
	
	

}
