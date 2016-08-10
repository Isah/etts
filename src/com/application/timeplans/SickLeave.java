package com.application.timeplans;

public class SickLeave extends Leave{


	//private String date_of_returntoduty;
    //private String doc_certificate_provided;
	private String description_illness;
	

	
	
	
	public SickLeave(String employeeno, String department, String date,String startDate,String endDate,int daysRequested,String leaveType, String leave_no){
    	super(employeeno,department, date, startDate,endDate, daysRequested,leaveType,leave_no);
	}



	public String getDescription_illness() {
		return description_illness;
	} 

	public void setDescription_illness(String description_illness) {
		this.description_illness = description_illness;
	}
	
	
	
	
	
	
}
