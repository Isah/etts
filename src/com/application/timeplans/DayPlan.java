package com.application.timeplans;

import java.sql.Date;

public class DayPlan {

	public final int maxdayplans = 5;
	
	
	private String client;
	private String servicecode;
	private String day;
	private int timeworked;  //hrs
	private String billingstate;
	private String section;
	private TimeWorked timeWorked;
	
	private int ID;
	private Date date;
	
	
	public TimeWorked getTimeWorked() {
			return timeWorked;
	}


	public void setTimeWorked(TimeWorked timeworked) {
			this.timeWorked = timeworked;
	}
	
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getClient() {
		return client;
	}
	public String getBillingstate() {
		return billingstate;
	}
	public void setBillingstate(String billingstate) {
		this.billingstate = billingstate;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getServicecode() {
		return servicecode;
	}
	public void setServicecode(String servicecode) {
		this.servicecode = servicecode;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public int getTimeworked() {
		return timeworked;
	}
	public void setTimeworked(int timeworked) {
		this.timeworked = timeworked;
	}
	public void setDate(Date date) {
		// TODO Auto-generated method stub
		this.date = date;
	}
	
	public Date getDate(){
		return date;
		
	}
	
	
	
	
	
}
