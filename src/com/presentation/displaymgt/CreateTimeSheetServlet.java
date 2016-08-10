package com.presentation.displaymgt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.application.scheduler.Schedule;
import com.application.timeplans.DayPlan;
import com.application.timeplans.TimeSheet;
import com.application.worktimemgt.BeerExpert;
import com.data.dataFacade.AbstractDAOFactory;
import com.data.dataFacade.TimeSheetDataFacade;
import com.data.worktimedatamgt.TimeSheetDAO;

import javax.servlet.*;

@SuppressWarnings("serial")

public class CreateTimeSheetServlet extends HttpServlet {
	

	//TimeSheetManager manage = null;
	HttpSession session;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
		String tsheet_name = request.getParameter("newtimesheet_name");
		request.setAttribute("time_name", tsheet_name);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter(); 
		
		
		//TimeSheetDataFacade manage = new TimeSheetDataFacade();
		//manage
		
		
		
		//request.getRequestDispatcher("/timeSheetManager.jsp").forward(request, response);
		 		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
		//response.setContentType("text/html");
		PrintWriter out = response.getWriter(); 
		//String form_selector = request.getParameter("formName");
	//	
		//if(form_selector.equals("newTimesheet")){
			
			
			  //UserLoginValidation user = (UserLoginValidation)session.getAttribute("user");
			
			  //TimeSheet sheet = new TimeSheet();
			//  int key = manage.getDayPlans().get(0).getID();
		//	  List<DayPlan> plans = manage.getDayPlans();
	       //   Date dato = searchEngine(key,plans);		  
	          
	          
	     //     sheet.setDateFro(dato.toString());
			  //sheet.setDateTo(dateTo);
		//	  sheet.setEmployee_no(manage.getEID(user.getUsername(), user.getPassword()));  
			//  sheet.setDayplans(plans);
			  
			//  manage.submitTimeSheet(sheet);
			  		    
	//	}	
		
		
	//if(form_selector.equals("newDayplan")){	
		TimeSheetDataFacade facade = new TimeSheetDataFacade();
		
		ServletContext context = getServletContext();
		String username = (String)context.getAttribute("user");
		String password = (String)context.getAttribute("pass");
		
		//session staff
		HttpSession session = request.getSession();
		 //UserLoginValidation user = (UserLoginValidation)session.getAttribute("user");
		 //String username = (String)session.getAttribute("username");
		 //String password = (String)session.getAttribute("password");
		int employeeid = facade.getEID(username, password);
		
		
		//out.println("ID"+employeeid);
		
		String name = request.getParameter("timesheet_name");
		Schedule schedule = new Schedule();
		String date = schedule.getCurrentDate();
		
		TimeSheet sheet = new TimeSheet();
		sheet.setDateofcreation(date);
		sheet.setEmployee_id(employeeid);  
		sheet.setTimeSheetName(name); 
		
		String client = request.getParameter("client_name");
		String day = request.getParameter("client_date");  

	    
		
		
		//String tdate = schedule.convertToSqlDate_pString(day);
		
		
	    String serviceCode = request.getParameter("service_code");
		String billingState = request.getParameter("billable");
		String timeWorked = request.getParameter("time_worked");
		String section = request.getParameter("section");
		
		
				DayPlan dayplan = new DayPlan();
				dayplan.setBillingstate(billingState);  
				dayplan.setClient(client);
				dayplan.setDay(day);
				dayplan.setTimeworked(Integer.parseInt(timeWorked));
				dayplan.setServicecode(serviceCode);
				dayplan.setSection(section);
				
				
						TimeSheetDataFacade tfacade = new TimeSheetDataFacade();
						//tfacade.submitTimeSheet(sheet, dayplan);
                  		
						
					//	List<TimeSheet> sts = tfacade.searchTimeSheet("", "", "All-All", 1);
						
						
						
					//	out.println("Size "+sts.size());
						//out.println("date "+sts.get(0).getDateofcreation());
						
						
						
			            request.getRequestDispatcher("/timeSheetManager.jsp").forward(request, response);
	
	

}
/*	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String selectedItem = request.getParameter("selectedValue");


        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if(selectedItem == ("date")){
                   out.println("am working");     
            }else{}


        } finally {            
            out.close();
        }
    }
*/
	private Date searchEngine(int key, List<DayPlan> list){
		
		Date date = null;
		
		for(int i=0; i<list.size(); i++){
			
			if(list.get(i).getID() == key){
				
			    date = list.get(i).getDate();
			}
			else{
				
				date = null;
			}
			
		}
		
		return date;
		
	}
	
	
}
