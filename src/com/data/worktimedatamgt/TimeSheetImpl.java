package com.data.worktimedatamgt;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.application.staff.Employee;
import com.application.timeplans.Approval;
import com.application.timeplans.DayPlan;
import com.application.timeplans.TimeSheet;
import com.application.timeplans.TimeWorked;
import com.data.dataFacade.DatabaseConfig;
import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.PreparedStatement;
import com.google.cloud.sql.jdbc.ResultSet;
import com.google.cloud.sql.jdbc.Statement;

public class TimeSheetImpl implements TimeSheetDAO{

	
	List<TimeSheet> timesheetlist = null;
	Connection conn = null;
	
	public TimeSheetImpl(){
		
		 conn = DatabaseConfig.getMySqlInstance().connect();

		
	}
	
	
	String DATE_TIME_FORMAT = "yyyy-MM-dd";
    SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
    
    

	 ///////////////////////////////////TIMESHEET SHEET/////////////////////////////////////////////////
    
    
    
	@Override
	public boolean insertTimeSheet(TimeSheet timesheet) {
		// TODO Auto-generated method stub
		
		boolean value = false;
		int count =0;
		
	    
	    int employee_id = timesheet.getEmployee_id();
		String date = timesheet.getDateofcreation();
		String timeSheetName = timesheet.getTimeSheetName();
		int isSubmitted = 0;

		
		
String query1 = "insert into timesheet (employee_id,date_created,timesheet_name,isSubmitted) values ('"+employee_id+"' ,'"+date+"','"+timeSheetName+"','"+isSubmitted+"')";
         	    
	    int x=0;
	    try {
			PreparedStatement pstmt1 = conn.prepareStatement(query1);
			count = pstmt1.executeUpdate();
		//	addAllDayPlans(dayplanlist);
			if(count > 0){
				
				value = true;
			}
			else{
				
				value = false;
				
			}
	     	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return value;
	}

	@Override
	public TimeSheet getTimeSheet(String timesheetid) {
		
		
	          TimeSheet timesheet = null;
	             
           
              String query = "";
             //String position_in_co = getPositionInCompany(employeeid);
                 
              
                      
			//String query = "select timesheet_id,employee_id,date_created,timesheet_name  from timesheet where date_created > '"+dateFro+"' AND date_created < '"+dateTo+"'  AND timesheet_id != '"+idlist.get(i).toString()+"'  AND isSubmitted = 1";
			 
            //query = "select timesheet_id,employee_id,date_created from timesheet where  timesheet_id != '"+idlist.get(i).toString()+"'  AND isSubmitted = 1";
			 
			 
			  query = "select timesheet_id,employee_id,date_created from timesheet where  timesheet_id = '"+timesheetid+"'  AND isSubmitted = 1";
		
			 
			 
			try {
		
					
				int tid =0;
				int eid =0;
			    Date datecreated  = null;
			    String timeSheetName = null;
			
				Statement st= conn.createStatement();
				ResultSet rset = st.executeQuery(query);
			
				while(rset.next()){
			        tid = rset.getInt("timesheet_id");
			        eid = rset.getInt("employee_id");
			        datecreated = rset.getDate("date_created");
				   // timeSheetName = rset.getString("timesheet_name");
				
			     timesheet = new TimeSheet();
				
				timesheet.setTimesheet_id(tid);
				timesheet.setDateofcreation(datecreated.toString());
				timesheet.setEmployee_id(eid);

			    String querying = "";
							
			querying = "select timesheet_id,client,day,service_code,section,time_worked,billing_state  from dayplan where timesheet_id = '"+tid+"'";
						

			String client ="";
			String servicecode ="";
			String section ="";
			int timeworked =0;
			String billingstate =null;
			String day  = null;
			Date date = null;

			Statement stmt= conn.createStatement();
			ResultSet resultset = stmt.executeQuery(querying);

			DayPlan dayplan = null;
			List<DayPlan> plans = new ArrayList();
			
			while(resultset.next()){
				client = resultset.getString("client");
				servicecode = resultset.getString("service_code");
				section = resultset.getString("section");
				timeworked = resultset.getInt("time_worked");
				billingstate = resultset.getString("billing_state");
				day = resultset.getString("day");
				
				dayplan = new DayPlan();
				 
				 dayplan.setClient(client);
			     dayplan.setServicecode(servicecode);
			     dayplan.setSection(section);
			     dayplan.setBillingstate(billingstate);
			     dayplan.setDay(day);
			     dayplan.setTimeworked(timeworked);
				 plans.add(dayplan);
				
			}
			

			   timesheet.setDayplans(plans);
			 
			
				}
                                
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   
                 
		 
		
		return timesheet;
	}

	@Override
	public boolean isTimeSheetSubmitted(String timeSheetName) {
		// TODO Auto-generated method stub
		boolean isSubmitted = false;
		int value = 0;
		
		
		String sql = "select isSubmitted from timesheet where timeSheetName = '"+timeSheetName+"'";
		Statement st;
		try {
			st = conn.createStatement();
		
		ResultSet rset = st.executeQuery(sql);
		
		while(rset.next()){
			
			value = rset.getInt("isSubmitted");
			if(value == 1){
				
				isSubmitted = true;
				
			}
			else{
				
				isSubmitted = false;
				
			}
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isSubmitted;
	}

	@Override
	public boolean updateTimeSheet(String timesheetname) {
		// TODO Auto-generated method stub
		
		boolean update = false;
		int count =0;
		
		
		String sql = "update timesheet set isSubmitted = 1 where timesheet_name = '"+timesheetname+"'";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
		
		    count = ps.executeUpdate();
		
		    if(count>0){
		    	
		    	update = true;
		    }
		    else{
		    	
		    	update = false;
		    	
		    }
		    
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return update;
	}
	
	@Override
	public boolean deleteTimeSheet(int timesheet_id) {
		
		String query1 = "delete from dayplan where timesheet_id = '"+timesheet_id+"'  ";
		String query2 = "delete from timesheet where timesheet_id = '"+timesheet_id+"'";
		
		int count1 = 0;
		int count2 = 0;
		int count = 0;
		
		boolean isDeleted = false;
		
        try {
        	
			PreparedStatement ps1 = conn.prepareStatement(query1);
			PreparedStatement ps2 = conn.prepareStatement(query2);
			
			count1 = ps1.executeUpdate();
			count2 = ps2.executeUpdate();
			
			count = count1+count2;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
     
        
        if(count>0){
        	isDeleted = true;
        	
        }else{
        	
        	isDeleted = false;
        	
        }

		
		return isDeleted;
	}
    
	private int getTimeSheetID(int eno) {
		
		
		
		
		String q = "select employee_id from employee where employee_no = '"+eno+"'";
		int eID = DatabaseConfig.getMySqlInstance().getResultSetInt(q, conn);
		
		String qry = "select timesheet_id from timesheet where employee_id = '"+eID+"'";
		int tID = DatabaseConfig.getMySqlInstance().getResultSetInt(qry, conn);

		return tID;
	}

	
	 //---------------------------SEARCHING FOR A REPORT TIMESHEET METHODS --------------------------
	
	
	
	@Override
	 public List<TimeSheet> lookupTimeSheet(String dateFro, String employeeno, String searchby,String item,int approvestate) {
	     

			
			List<TimeSheet> timesheetlist = null;
		                    
	            List<Integer> idlistOfAppr = getIDListOfApprovals(approvestate);
	                 
	             timesheetlist = new ArrayList<>();
	             

	             List<Integer> idlist = getTimeSheetIDS(idlistOfAppr,searchby,item);
	             System.out.println("ID Size"+idlist.size());
	             
	             
	             for(int i=0; i<idlist.size(); i++){
	         
				 String query = "";
			
				if(!employeeno.equals("Any")){
				int empID = getEmployeeID(Integer.parseInt(employeeno));
				//query = "select timesheet_id,employee_id,date_created,timesheet_name  from timesheet where date_created > '"+dateFro+"' AND date_created < '"+dateTo+"' AND employee_id = '"+empID+"' AND timesheet_id = '"+idlist.get(i).toString()+"'  AND isSubmitted = 1";
				query = "select timesheet_id,employee_id,date_created,timesheet_name  from timesheet where employee_id = '"+empID+"' AND timesheet_id = '"+idlist.get(i).toString()+"'  AND isSubmitted = 1";
				
				}
				
				else {
				 //query = "select timesheet_id,employee_id,date_created,timesheet_name  from timesheet where date_created > '"+dateFro+"' AND date_created < '"+dateTo+"' AND timesheet_id = '"+idlist.get(i).toString()+"' AND isSubmitted = 1";
				 query = "select timesheet_id,employee_id,date_created,timesheet_name  from timesheet where timesheet_id = '"+idlist.get(i).toString()+"' AND isSubmitted = 1";
				
				}
				try {
				
					
					
					
					
						
					int tid =0;
					int eid =0;
					Statement st= conn.createStatement();
					ResultSet rset = st.executeQuery(query);
				    String querying = "";

					while(rset.next()){
				        tid = rset.getInt("timesheet_id");
				       // eid = rset.getInt("employee_id");
				       // datecreated = rset.getDate("date_created");
					
					
					//TimeSheet timesheet = new TimeSheet();
					//timesheet.setTimesheet_id(tid);
					//timesheet.setDateofcreation(datecreated.toString());
					//timesheet.setEmployee_id(eid);
				

				 
				String client ="";
				String servicecode ="";
				String section ="";
				int timeworked =0;
				String billingstate =null;
				String day  = null;
				Date date = null;
				int timesheet_id=0;
				
				DayPlan dayplan = null;
				List<DayPlan> plans = new ArrayList<>();
				
				
				
				if(searchby.equals("client")){

					querying = "select timesheet_id,client,day,mon,tue,wed,thur,fri,sat,service_code,section,time_worked,billing_state  from dayplan where timesheet_id = '"+tid+"' AND client = '"+item+"' ";
								
							
						}
					
						else if(searchby.equals("service_code")){
						
					 querying = "select timesheet_id,client,day,mon,tue,wed,thur,fri,sat,service_code,section,time_worked,billing_state  from dayplan where timesheet_id = '"+tid+"' AND  service_code = '"+item+"'  ";
								
						}
					   else if(searchby.equals("section")){
					 querying = "select timesheet_id,client,day,mon,tue,wed,thur,fri,sat,service_code,section,time_worked,billing_state  from dayplan where timesheet_id = '"+tid+"' AND  section = '"+item+"'  ";
						
						}
					   else if(searchby.equals("time_worked")){
					querying = "select timesheet_id,client,day,mon,tue,wed,thur,fri,sat,service_code,section,time_worked,billing_state  from dayplan where timesheet_id = '"+tid+"' AND   time_worked = '"+item+"' ";
						
					    }
					   else if(searchby.equals("billing_state")){
					querying = "select timesheet_id,client,day,mon,tue,wed,thur,fri,sat,service_code,section,time_worked,billing_state  from dayplan where timesheet_id = '"+tid+"' AND   billing_state = '"+item+"' ";
						
					   }
					   else if(searchby.equals("Any")){
					    querying = "select timesheet_id,client,day,mon,tue,wed,thur,fri,sat,service_code,section,time_worked,billing_state  from dayplan where timesheet_id = '"+tid+"' ";
					   		
					   }
				
				
				
				Statement stmt= conn.createStatement();
				ResultSet resultset = stmt.executeQuery(querying);

				
				
				
				
				
				
				while(resultset.next()){
					client = resultset.getString("client");
					servicecode = resultset.getString("service_code");
					section = resultset.getString("section");
					timeworked = resultset.getInt("time_worked");
					billingstate = resultset.getString("billing_state");
					day = resultset.getString("day");
					timesheet_id = resultset.getInt("timesheet_id");
					
					int monTime = resultset.getInt("mon");
					int tueTime = resultset.getInt("tue");
					int wedTime = resultset.getInt("wed");
					int thurTime = resultset.getInt("thur");
					int friTime = resultset.getInt("fri");
					int satTime = resultset.getInt("sat");
					
					TimeWorked timeWorked = new TimeWorked();
					timeWorked.setMon(monTime);
					timeWorked.setTue(tueTime);
					timeWorked.setWed(wedTime);
					timeWorked.setThur(thurTime);
					timeWorked.setFri(friTime);
					timeWorked.setSat(satTime);  
				  //  date = resultset.getDate("date");
					 dayplan = new DayPlan();
					 
					 dayplan.setClient(client);
				     dayplan.setServicecode(servicecode);
				     dayplan.setSection(section);
				     dayplan.setBillingstate(billingstate);
				     dayplan.setDay(day);
				     dayplan.setTimeworked(timeworked);
				     dayplan.setTimeWorked(timeWorked);
				     dayplan.setID(tid);
				     
				  //   dayplan.setDate(date);
					 plans.add(dayplan);
					
			     	}
				
					timesheetlist.add(getTimeSheetOfID(tid,plans));
					}
	                                
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	   
	            }
			 
			
			return timesheetlist;
		}
	
	private List<Integer> getTimeSheetIDS(List<Integer> idlist,String searchby,String item){
		String querying = "";
		List<Integer> ids = null;
		List<Integer> alist = getIDListOfApprovals(1);
		 ids = new ArrayList<Integer>();
		 
		 
		      for(int i=0; i<idlist.size(); i++){
		    	  
		    	
		    	  
		      if(searchby.equals("client")){
		    	 
			   querying = "select timesheet_id from dayplan where client = '"+item+"' AND timesheet_id = '"+idlist.get(i).toString()+"' ";
			   int id = DatabaseConfig.getMySqlInstance().getResultSetInt(querying, conn);
			   if(id != 0){
				   System.out.println("Im in the loop ID"+id);
				      ids.add(id);
			   }

			}
		      
		      
		      else if(searchby.equals("service_code")){
					
					 querying = "select timesheet_id from dayplan where service_code = '"+item+"' AND  timesheet_id ='"+idlist.get(i).toString()+"'  ";
					 int id = DatabaseConfig.getMySqlInstance().getResultSetInt(querying, conn);
				
					   if(id != 0){
						   System.out.println("Im in the loop ID"+id);
						      ids.add(id);
					   }
						}
			  else if(searchby.equals("section")){
					 querying = "select timesheet_id from dayplan where section = '"+item+"' AND timesheet_id ='"+idlist.get(i).toString()+"' ";
					 int id = DatabaseConfig.getMySqlInstance().getResultSetInt(querying, conn);
					  if(id != 0){
						   System.out.println("Im in the loop ID"+id);
						      ids.add(id);
					   }
				    
					 
					 
						}
		      else if(searchby.equals("time_worked")){
					querying = "select timesheet_id from dayplan where time_worked = '"+item+"' AND  timesheet_id = '"+idlist.get(i).toString()+"'";
					int id = DatabaseConfig.getMySqlInstance().getResultSetInt(querying, conn);
					  if(id != 0){
						   System.out.println("Im in the loop ID"+id);
						      ids.add(id);
					   }
					    }
			  else if(searchby.equals("billing_state")){
					querying = "select timesheet_id from dayplan where billing_state = '"+item+"' AND  timesheet_id ='"+idlist.get(i).toString()+"' ";
				    
				      int id = DatabaseConfig.getMySqlInstance().getResultSetInt(querying, conn);
				      if(id != 0){
						   System.out.println("Im in the loop ID"+id);
						      ids.add(id);
					   }
						
			           }
	
			   else if(searchby.equals("Any")){
					querying = "select timesheet_id from dayplan where  timesheet_id ='"+idlist.get(i).toString()+"' ";
					ids = alist;	
					   }
		      
		  
		      
		      }
		      
		      
		
		      return ids;
		
		
	}
	
	
	
	private TimeSheet getTimeSheetOfID(int id,List<DayPlan> plans){
		
		TimeSheet tsheet = null;
		
		String q = "select employee_id,date_created from timesheet where timesheet_id like '"+id+"'";
		try {
			Statement st = conn.createStatement();
			ResultSet rset = st.executeQuery(q);
			
			tsheet = new TimeSheet();
			while(rset.next()){
				
				int employee_id = rset.getInt("employee_id");
			    String date_created = rset.getString("date_created");	
				tsheet.setDateofcreation(date_created);
				tsheet.setEmployee_id(employee_id);
				
				
				
			}
			tsheet.setDayplans(plans);
			tsheet.setTimesheet_id(id);
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tsheet;
		
		
	}
	
	 public List<Integer> getIDListOfApprovals(int state){
         
         ResultSet rset = null;
         List<Integer> list = null;
         String q2 = "select timesheet_id from approval where approval_state = '"+state+"'  ";
         
         
      try {
          String q = "select timesheet_id from approval where approval_state = '"+state+"'";
          Statement statement = conn.createStatement();
          rset = statement.executeQuery(q);
          list = new ArrayList<>();
          while(rset.next()){
          int a = rset.getInt("timesheet_id");
          list.add(a);
          
          }
          
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
			
     
     return list;
     
     
     }
    
	 
	 
	 //-----------------------------END OF REPORT TIMESHEET METHODS -----------------------

	
	 /////////////////////////////////////END OF TIME SHEETS ////////////////////////////////////////////////
	 
	
	
	@Override
	public boolean insertDayPlan(DayPlan plan) {

		boolean value = false;
        int count =0;
        
	   String sql = "insert into dayplan (timesheet_id,client,day,service_code,section,time_worked,billing_state) values ((select max(timesheet_id) from timesheet), '"+plan.getClient()+"', '"+plan.getDay()+"', '"+plan.getServicecode()+"' ,'"+plan.getSection()+"','"+plan.getTimeworked()+"', '"+plan.getBillingstate()+"')";
	   try {         
       PreparedStatement p = conn.prepareStatement(sql);
       count = p.executeUpdate();
		//	addAllDayPlans(dayplanlist);
			if(count > 0){
				
				value = true;
			}
			else{
				
				value = false;
				
			}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	        //////////////////////////////////  
	   
	   return value;
	   
		
	}

	@Override
	public List<DayPlan> getAllDayPlans(String employeeno) {
		// TODO Auto-generated method stub
		
		List<DayPlan> list = null;
		String query = "select employee_id,client,day,service_code,section,time_worked,billing_state from dayplan  where timesheet_id = (select timesheet_id from timesheet where employee_id = (select employee_id from employee where employee_no = '"+employeeno+"'))  ";
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet resultset = stmt.executeQuery(query);
			
			list = new ArrayList<>();
			
			while(resultset.next()){
				
				int employeeID = resultset.getInt("employee_id");
				String client = resultset.getString("client");
				String day = resultset.getString("day");
				String scode = resultset.getString("service_code");
				String section = resultset.getString("section");
				int hours = resultset.getInt("time_worked");
				String bstate = resultset.getString("billing_state");
				
		        DayPlan plan = new DayPlan();
		        
		        plan.setBillingstate(bstate);
		        plan.setClient(client);
		        plan.setDay(day);
		        plan.setID(employeeID);
		        plan.setSection(section);
		        plan.setServicecode(scode);
		        plan.setTimeworked(hours);

		      
				list.add(plan);
				
			}
			
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return list;
	}
	
	

	
	

///////////////////////PENDING AND APPROVD TIMESHEETS METHODS //////////////////////////////////////////////////////////////
	@Override
	public int getNoOfApprovedTSheets() {
		
	int result =0;	
	String approveSql = "select count(approval_id) from approval where approval_state = 1";
	try {
		
		Statement statement = conn.createStatement();
		ResultSet rset = statement.executeQuery(approveSql);
		while(rset.next()){
			
		result =	rset.getInt(1);
			
		}
		
		
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		
		
		
		return result;
	}


	@Override
	public int getNoOfPendingTSheets() {
		int result =0;	
		String approveSql = "select count(timesheet_id) from timesheet where isSubmitted = 1";
		try {
			
			Statement statement = conn.createStatement();
			ResultSet rset = statement.executeQuery(approveSql);
			while(rset.next()){
				
			result =	rset.getInt(1);
				
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
			
			
			return result;
	}


	@Override
	public int getNoOfRejectedTSheets() {
		int result =0;	
		String approveSql = "select count(approval_id) from approval where approval_state  = 0";
		try {
			
			Statement statement = conn.createStatement();
			ResultSet rset = statement.executeQuery(approveSql);
			while(rset.next()){
				
			result =	rset.getInt(1);
				
			}
			
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

			return result;
	}

	
	

	@Override
	public boolean recordApproval(int timesheetID, int approvalstate,int employee_id,String dating) {
		boolean value = false;
		int count = 0;
		
		// int id = getEmployeeID(employee_no);
		
			//Stri ng q = "select person_in_mgt_id from person_in_mgt where employee_id = '"+id+"'";
			//int pID = DatabaseConfig.getMySqlInstance().getResultSetInt(q, conn);
			   String sql = "insert into approval (person_in_mgt_id,timesheet_id,date,approval_state) values ((select person_in_mgt_id from person_in_mgt where employee_id = '"+employee_id+"'), '"+timesheetID+"', '"+dating+"', '"+approvalstate+"')";
			   try {         
		       PreparedStatement p = conn.prepareStatement(sql);
		       count = p.executeUpdate();
				//	addAllDayPlans(dayplanlist);
					if(count > 0){
						
						value = true;
					}
					else{
						
						value = false;
						
					}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 
		return value;
	}
	
	@Override
	public List<TimeSheet> getApprovedTSheets(String dateFro, String dateTo, String filter, String employeeid) {

		
		  String position_in_co = getPositionInCompany(employeeid);
          
		  
		
		
		
			      List<TimeSheet> timesheetlist = null;
		                    
	              List<Integer> idlist = getIDListOfApprovals(1);
	                 
	             for(int i=0; i<idlist.size(); i++){
	                      
				 String query = "";
				 if(position_in_co.equals("Manager") || position_in_co.equals("Partner")){
//						query = "select timesheet_id,employee_id,date_created,timesheet_name  from timesheet where date_created > '"+dateFro+"' AND date_created < '"+dateTo+"'  AND timesheet_id = '"+idlist.get(i).toString()+"'  AND isSubmitted = 1";
						query = "select a.person_in_mgt_id, a.date,a.approval_state, t.timesheet_id,t.employee_id,t.date_created from approval as a inner join timesheet as t on a.timesheet_id = t.timesheet_id where t.timesheet_id = '"+idlist.get(i).toString()+"'  AND isSubmitted = 1";
					
				  }
				  else{

						query = "select a.person_in_mgt_id, a.date,a.approval_state, t.timesheet_id,t.employee_id,t.date_created from approval as a inner join timesheet as t on a.timesheet_id = t.timesheet_id where t.timesheet_id = '"+idlist.get(i).toString()+"'  AND employee_id = '"+employeeid+"' AND isSubmitted = 1";
					
				  }

				
			
				try {
				 timesheetlist = new ArrayList<>();
						
					int tid =0;
					int eid =0;
					
				    String datecreated  = null;
				    String timeSheetName = null;
				    
					Statement st= conn.createStatement();
					ResultSet rset = st.executeQuery(query);
				
					while(rset.next()){
				        tid = rset.getInt("timesheet_id");
				        eid = rset.getInt("employee_id");
				        datecreated = rset.getString("date_created");
					   // timeSheetName = rset.getString("timesheet_name");
					    
					    String appdate = rset.getString("date");
					    int appstate = rset.getInt("approval_state");
					    int person_id = rset.getInt("person_in_mgt_id");
					    //int tsID = rset.getInt("timesheet_id ");
					    
					    Approval approval = new Approval();
					    approval.setApprovalState(appstate);
					    approval.setDate(appdate);
					    approval.setPerson_in_mgt_id( person_id);
					    //approval.setTimesheetID(tsID);
					
					TimeSheet timesheet = new TimeSheet();
				     
					timesheet.setTimesheet_id(tid);
					timesheet.setDateofcreation(datecreated.toString());
					timesheet.setTimeSheetName(timeSheetName);
                    timesheet.setEmployee_id(eid);
                    timesheet.setApproval(approval);  
				    String querying = "";
								
				querying = "select timesheet_id,client,day,service_code,section,time_worked,billing_state  from dayplan where timesheet_id = '"+tid+"'";
							
	
				String client ="";
				String servicecode ="";
				String section ="";
				int timeworked =0;
				String billingstate =null;
				String day  = null;
				

				Statement stmt= conn.createStatement();
				ResultSet resultset = stmt.executeQuery(querying);

				DayPlan dayplan = null;
				List<DayPlan> plans = new ArrayList();
				
				while(resultset.next()){
					client = resultset.getString("client");
					servicecode = resultset.getString("service_code");
					section = resultset.getString("section");
					timeworked = resultset.getInt("time_worked");
					billingstate = resultset.getString("billing_state");
					day = resultset.getString("day");
				  //  date = resultset.getDate("date");
					 dayplan = new DayPlan();
					 
					 dayplan.setClient(client);
				     dayplan.setServicecode(servicecode);
				     dayplan.setSection(section);
				     dayplan.setBillingstate(billingstate);
				     dayplan.setDay(day);
				     dayplan.setTimeworked(timeworked);
				  //   dayplan.setDate(date);
					 plans.add(dayplan);
					
				}
				

				    timesheet.setDayplans(plans);
				 
					timesheetlist.add(timesheet);
					}
	                                
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	   
	                 }
			 
			
			return timesheetlist;
		}

	@Override
	public List<TimeSheet> getPendingTSheets(String dateFro, String dateTo, String filter, String employeeid) {
		
		List<TimeSheet> timesheetlist = null;
	                    
                 List idlist = getIDListOfApprovals(1);
           
                 String query = "";
              String position_in_co = getPositionInCompany(employeeid);
                 
              for(int i=0; i<idlist.size(); i++){
                      
			//String query = "select timesheet_id,employee_id,date_created,timesheet_name  from timesheet where date_created > '"+dateFro+"' AND date_created < '"+dateTo+"'  AND timesheet_id != '"+idlist.get(i).toString()+"'  AND isSubmitted = 1";
			 if(position_in_co.equals("Manager") || position_in_co.equals("Partner")){
             query = "select timesheet_id,employee_id,date_created from timesheet where  timesheet_id != '"+idlist.get(i).toString()+"'  AND isSubmitted = 1";
			 }
			 else{
			  query = "select timesheet_id,employee_id,date_created from timesheet where  timesheet_id != '"+idlist.get(i).toString()+"' AND employee_id = '"+employeeid+"' AND isSubmitted = 1";
		
			 }
			 
			try {
			 timesheetlist = new ArrayList<>();
					
				int tid =0;
				int eid =0;
			    Date datecreated  = null;
			    String timeSheetName = null;
			
				Statement st= conn.createStatement();
				ResultSet rset = st.executeQuery(query);
			
				while(rset.next()){
			        tid = rset.getInt("timesheet_id");
			        eid = rset.getInt("employee_id");
			        datecreated = rset.getDate("date_created");
				   // timeSheetName = rset.getString("timesheet_name");
				
				TimeSheet timesheet = new TimeSheet();
				
				timesheet.setTimesheet_id(tid);
				timesheet.setDateofcreation(datecreated.toString());
				timesheet.setEmployee_id(eid);

			    String querying = "";
							
			querying = "select timesheet_id,client,day,service_code,section,time_worked,billing_state  from dayplan where timesheet_id = '"+tid+"'";
						

			String client ="";
			String servicecode ="";
			String section ="";
			int timeworked =0;
			String billingstate =null;
			String day  = null;
			Date date = null;

			Statement stmt= conn.createStatement();
			ResultSet resultset = stmt.executeQuery(querying);

			DayPlan dayplan = null;
			List<DayPlan> plans = new ArrayList();
			
			while(resultset.next()){
				client = resultset.getString("client");
				servicecode = resultset.getString("service_code");
				section = resultset.getString("section");
				timeworked = resultset.getInt("time_worked");
				billingstate = resultset.getString("billing_state");
				day = resultset.getString("day");
				
				dayplan = new DayPlan();
				 
				 dayplan.setClient(client);
			     dayplan.setServicecode(servicecode);
			     dayplan.setSection(section);
			     dayplan.setBillingstate(billingstate);
			     dayplan.setDay(day);
			     dayplan.setTimeworked(timeworked);
				 plans.add(dayplan);
				
			}
			

			   timesheet.setDayplans(plans);
			 
				timesheetlist.add(timesheet);
				}
                                
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   
                 }
		 
		
		return timesheetlist;
	}

/////////////////////////////////// END OF PENDING AND APPROVED TIMESHEETS//////////////////////////////////////////////
	
	
	
	
	
	
	
	/////////////////////////////////////////////////////EMPLOYEES////////////////////////////
	

	@Override
	public int getEmployeeIDFromPersonInMgt(int id) {
		// TODO Auto-generated method stub
		int eid =0;
		String q = "select employee_id from person_in_mgt where person_in_mgt_id = '"+id+"'";	
		try {
			Statement st = conn.createStatement();
			ResultSet set = st.executeQuery(q);
			
			while(set.next()){
			
				eid = set.getInt("employee_id");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return eid;
	}

    private String getPositionInCompany(String employeeid){
    	   String position_in_co = null;
	    String query = "select position_in_co from employee where employee_id = '"+employeeid+"'";
	   
	    try {
			Statement st = conn.createStatement();
			ResultSet rset = st.executeQuery(query);
			
			while(rset.next()){
				
	     	position_in_co = rset.getString("position_in_co");
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    return position_in_co;
	    
	    
        }

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employeelist = null;
		Employee employee = null;
		String sql = "select * from employee ";
		
		try {
			Statement st = conn.createStatement();
			ResultSet result = st.executeQuery(sql);
			employeelist = new ArrayList<>();
			
			while(result.next()){
				employee = new Employee();
			String fname = 	result.getString("first_name");
			String lname = 	result.getString("last_name");
			//String dob =	result.getString("dob");
			//String mstatus = result.getString("marital_status");
	   		int idno = result.getInt("id_no");
			int eno =  result.getInt("employee_no");
			////int brate =	result.getInt("billing_rate");
			String pcompany = result.getString("position_in_co");
			//int nodependents = result.getInt("no_of_dependents");
			//String nextofkin = result.getString("next_of_kin");
			//String 	datejoined = result.getString("date_joined_co");
				
			
			employee.setFirstname(fname);
			employee.setLastname(lname);
			employee.setIdno(idno);
			employee.setEmployee_no(eno); 
			employee.setPosition_in_co(pcompany); 
			employeelist.add(employee);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return employeelist;
	}
	
	
	@Override
	public Employee getEmployee(String employeeid) {
		Employee employee = new Employee();
		String sql = "select first_name,last_name,id_no,employee_no,position_in_co from employee where employee_id = '"+employeeid+"' ";
		
		try {
			Statement st = conn.createStatement();
			ResultSet result = st.executeQuery(sql);
			
			while(result.next()){
			String fname = 	result.getString("first_name");
			String lname = 	result.getString("last_name");
			//String dob =	result.getString("dob");
			//String mstatus = result.getString("marital_status");
	   		int idno = result.getInt("id_no");
			int eno =  result.getInt("employee_no");
			////int brate =	result.getInt("billing_rate");
			String pcompany = result.getString("position_in_co");
			//int nodependents = result.getInt("no_of_dependents");
			//String nextofkin = result.getString("next_of_kin");
			//String 	datejoined = result.getString("date_joined_co");
				
			
			employee.setFirstname(fname);
			employee.setLastname(lname);
			employee.setIdno(idno);
			employee.setEmployee_no(eno); 
			employee.setPosition_in_co(pcompany); 
			}
			
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		return employee;
	}

	
	private int getEmployee_No(int eid){
		String q = "select employee_no from employee where employee_id = '"+eid+"'";
		int eID = DatabaseConfig.getMySqlInstance().getResultSetInt(q, conn);
		
		return eID;
		
	}


	private int getEmployeeID(int employeeno) {
		
		
		String q = "select employee_id from employee where employee_no = '"+employeeno+"'";
		int eID = DatabaseConfig.getMySqlInstance().getResultSetInt(q, conn);
		
		
		
		return eID;
	}


	
	
	
	

	
	
	
	//METHOD NOT USED
	public List<TimeSheet> getApprovedTSheetsoK(String startDate, String endDate, String filter) {
	
		
		List<TimeSheet> sheetlist  =null;
		List<TimeSheet> tempsheetlist  =null;
		List<DayPlan> planlist = null;
		
		String approvedSql = "select d.client,d.day,d.service_code,d.section,d.time_worked,d.billing_state,  t.timesheet_id,t.timesheet_name,t.date_created,t.employee_id from dayplan as d inner join timesheet as t on d.timesheet_id = t.timesheet_id where t.timesheet_id = (select timesheet_id from approval where approval_state = 1)   AND t.date_created > '"+startDate+"' AND t.date_created < '"+endDate+"' order by '"+filter+"' asc  ";
		try {
			Statement st = conn.createStatement();
			ResultSet rset = st.executeQuery(approvedSql);
			
			sheetlist = new ArrayList<>();
			tempsheetlist = new ArrayList<>();
			
			planlist = new ArrayList<>();

			
			
			String client = "";
			String day = "";
			String service_code = "";
			String section = "";
			int time_worked = 0;
			String billing_state = "";
	        
			String tsheet_date_created = "";
			String timeSheetName = "";
			int timesheet_id = 0;
			int employee_id = 0;
			int employee_no = 0;
			
			
			
			
			
			while(rset.next()){
				client = rset.getString("client");
				day = rset.getString("day");
				service_code = rset.getString("service_code");
				section = rset.getString("section");
				time_worked = rset.getInt("time_worked");
				billing_state = rset.getString("billing_state");
		        
				tsheet_date_created = rset.getString("date_created");
				timeSheetName = rset.getString("timesheet_name");
				timesheet_id = rset.getInt("timesheet_id");
				employee_id = rset.getInt("employee_id");
				
				employee_no = getEmployee_No(employee_id);
				
				TimeSheet sheet = new TimeSheet();
				sheet.setDateofcreation(tsheet_date_created);
				//sheet.setEmployee_no(employee_no);
				sheet.setTimesheet_id(timesheet_id);
				sheet.setTimeSheetName(timeSheetName);
			    
				
				client = rset.getString("client");
				day = rset.getString("day");
				service_code = rset.getString("service_code");
				section = rset.getString("section");
				time_worked = rset.getInt("time_worked");
				billing_state = rset.getString("billing_state");
		        
				
				
				DayPlan plan = new DayPlan();
			    plan.setBillingstate(billing_state);
			    plan.setClient(client);
			    //plan.setDate(date);
			    plan.setDay(day);
			    plan.setSection(section);
			    plan.setServicecode(service_code);
			    plan.setTimeworked(time_worked);
				
			    
			    planlist.add(plan);
			    
			    
			    
			    
			    
			    
			}
			

			
			
			
	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		return null;
	}



	

}
