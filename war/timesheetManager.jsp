<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.List" %>
<%@ page import="com.application.timeplans.TimeSheet" %>
<%@ page import="com.application.performancemgt.*" %>
<%@ page import="com.application.scheduler.*" %>
<%@ page import="com.application.staff.*" %>
<%@ page import="com.data.dataFacade.*" %>
<%@ page import="com.application.timeplans.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Untitled Document</title>
<link href="managesheet.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="new.js"></script>

<script type="text/javascript" src="jquery-2.1.4.min.js"></script>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<!--
<script type="text/javascript" src="jquery-1.4.2.js"></script>
<script type="text/javascript" src="jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="jquery-1.10.2.min.js"></script>
-->
 <script type="text/javascript" src="jquery-1.12.0.min.js"></script> 
<script>
$(document).ready(function(){
  $(".cts").addClass("newactive");
  //timesheet form
  $(".client_btn2").click(function(){
   // $("form").slideDown();
    $(".manage_table").slideDown();
    $(".name_title").hide();
   // var test = $("#myform_ts :input").serializeArray();

    //$.post("UserEventHandler", test, function(json)){

      //if(json.status == "fail"){
        //alert(json.message);
      //}
      //if(json.status == "success"){
        //alert(json.message);
      //}
    //}"json");
  
   // var values = $("#myform_ts :input").serializeArray();
   var values = "test";
    $.ajax({
      type:'POST',
      url:'UserEventHandler',
     // dataType:'text/plain',
      data:{'test':values},
      statusCode:{
        404:function()
        {$("#message1").text("page not found");},
        500:function()
        {$("#message1").text("internal server error");}},
      success:function(result){
          $("#message1").html(result);
          console.log(values);
      }
    });

    //json
/*
    $.ajax({
      url:"UserEventHandler",
      type:'POST',
      data:JSON.stringify({'name':'anon'}),
      processData :true;
      //dataType:"json",
      contentType: "apllication/json; charset=UTF-8",
      complete: callback,
      success:function(data,textStatus,jqXHR)
    });    
*/
  });

  $("#myform_ts").submit(function(){
    return false;
  });

  $(".ts_button").click(function(){

    $(".name_title").slideDown();
    $(".manage_table").slideUp();
    //$(".name_title").hide();

  });


  //the overlay div
  $('.shell_three tbody tr').click(function(){
    $(".time_off_info").show();
    $(".ts_dialog").hide();
    $('.overlay').fadeIn();
  });
  $('.close-image').click(function(){
    $('.overlay').fadeOut();
  });

  $('.cts').click(function(){
    $(".cts").addClass("newactive");
    $('.shell_one').show();
    $('.shell_two').hide();
    $('.shell_three').hide();
    $('.shell_four').hide();

    $(".pts").removeClass("newactive");
    $(".ats").removeClass("newactive");
    $(".report").removeClass("newactive");
  });

  $('.ats').click(function(){
    $(".ats").addClass("newactive");
    $(".cts").removeClass("newactive");
    $(".pts").removeClass("newactive");
    $(".report").removeClass("newactive");

    $('.shell_one').hide();
    $('.shell_three').hide();
    $('.shell_four').hide();
    $('.shell_two').show();
  });

  $('.pts').click(function(){
    $('.pts').addClass("newactive");
    $(".cts").removeClass("newactive");
    $(".ats").removeClass("newactive");
    $(".report").removeClass("newactive");

    $('.shell_one').hide();
    $('.shell_two').hide();
    $('.shell_four').hide();
    $('.shell_three').show();
  });

  $('.report').click(function(){
    $('.report').addClass("newactive");
    $(".cts").removeClass("newactive");
    $(".ats").removeClass("newactive");
    $(".pts").removeClass("newactive");

    $('.shell_one').hide();
    $('.shell_two').hide();
    $('.shell_three').hide();
    $('.shell_four').show();
  });

  });
</script>

</head>
<body>
<!--overlay-->
  <div class="overlay">
    <div class="time_off_info">
      <div class="small_header">Approve TimeSheet<img src="images/cancel30 (1).png" class="close-image"></div>
      <div class="leave_info_group">

        <h4>General Information</h4>

        <div class="inner_info">
        
        <%   
             
             TimeSheetDataFacade tSheet = new TimeSheetDataFacade();
             ServletContext context = getServletContext();
		     String username = (String)context.getAttribute("user");
		     String password = (String)context.getAttribute("pass");
		
             TimeSheet timesheet = tSheet.getTimeSheet("17"); 
             
             String employee_name = tSheet.getEmployee(Integer.toString(timesheet.getEmployee_id())).getFirstname();
             String datecreated = timesheet.getDateofcreation();
             
             //String name = tSheet.getEmployee(Integer.toString(employeeid)).getFirstname();
             
             //tSheet.getPendingTSheets("", "", "", employeeid);
             
        
        %>
              
          <label class="req_info"><%= employee_name %></label>
          <label class="req_name">Employee Name</label>
        </div>
        <div class="inner_info">
          <label class="req_info"><%= datecreated %></label>
          <label class="req_name">Submitted on</label>
        </div>

      </div>

      <div class="leave_info_group">

        <h4>TimeSheet Information</h4>

        <table>
          <thead>
            <tr>
                 <th>Day</th>
                 <th>Client</th>
                 <th>Service Code</th>
                 <th>Section</th>
                 <th>Billing State</th>
                 <th>Time Worked</th>
                
            </tr>
          </thead>
          <tbody>
          <%    for(int i=0; i<timesheet.getDayplans().size(); i++){ %>
          
          <tr>
                <td><%= timesheet.getDayplans().get(i).getDay() %></td>
                <td><%= timesheet.getDayplans().get(i).getClient() %> </td>
                <td> <%= timesheet.getDayplans().get(i).getServicecode() %> </td>
                <td><%= timesheet.getDayplans().get(i).getSection() %> </td>
                <td><%= timesheet.getDayplans().get(i).getBillingstate() %> </td>
                <td><%= timesheet.getDayplans().get(i).getTimeworked() %> </td>
               
          </tr>
          
          <% }%>
          
          </tbody>
      </table>
      

      </div>

      <div class="leave_info_group">

        <h4>Status Information</h4>

        <div class="inner_info">
          <label class="req_info">Pending</label>
          <label class="req_name">Time Off Status</label>
        </div>
         
      </div>

      <div class="leave_info_group">
        <h4>change status</h4>
        <p>Please select which action you would like to take</p>

        <div class="inner_info">
          <button class="ggg">Approve TimeSheet</button> <button>Reject TimeSheet</button> <button>Cancel</button>
        </div> 
      </div>

    </div>
  </div>
  <!--start of the main staff-->

<div class="wrapper">
<div class="header"> Employee Time Tracking System </div>
<div class="maincontent">
<div class="ts_manager">
    <div class="top">Manage Timesheets</div>
<div class="shell">
	<div class="list_one">
	<ul class="list_ones">
		<li class="cts"><img src="images/sheet3.png" class="list_icon">New TimeSheet</li>
		<li class="ats"><img src="images/checked-files.png" class="list_icon">Approved TS</li>
		<li class="pts"><img src="images/erase-file.png" class="list_icon">Pending TS</li>
    <li class="report"><img src="images/seo-report.png" class="list_icon">Report</li>
	</ul>
	</div>
	
	<div class="shell_one">

		<div class="div_manage_table1">

      <div class="name_title">  
      <label>Enter client information and add to your TimeSheet</label>
      
        <input type="hidden" value="createTimeSheet" id="ts_value" name="ts_value">
        <button type="" class="client_btn2">New TimeSheet</button>
  
    </div>
      
     <!-- <form action="CreateTimeSheet" method="post">
        <div class="form_group">
          <label>Client Name</label><br>
          <input type="search" name="client_name">
        </div>
        <div class="form_group">
          <label>Date</label><br>
            <input type="date" name="client_date">
        </div>
        <div class="form_group">
          <label>Section Code</label><br>
          <div class="myselect">
          <select name="serviceCode" class="service_code">
            <option value="Audit">AUD</option>
            <option value="Tax Consultancy">TAX</option>
            <option value="Accountancy">ACC</option>
            <option value="Administration">ADMIN</option>
            <option value="Management Consultancy">CON</option>
            <option value="No Work">IDLE</option>
            <option value="training Including Self">TRA</option>
          </select>
        </div>
        </div>
        <div class="form_group">
          <label>Billing State</label><br>
          <div class="myselect">
          <select name="billable">
            <option>billable</option>
            <option>Non-bllable</option>
          </select>
        </div>
        </div>
        <div class="form_group">
          <label>Time Worked</label><br>
          <input type="number" name="time_worked">
        </div>
        <div class="form_group">
          <label>Section</label><br>
          <input type="search" name="section">
        </div><br>
<button class="client_btn1" type="submit"><img src="images/close-circular-button.png" class="list_icon1">Delete Client</button>
<button class="client_btn"><img src="images/rounded-add-button.png" class="list_icon1">Add Client</button> 
      </form>  -->

      <div class="manage_table">
        
      <table >
          <thead>
            <tr>
                 <th width="3%"></th>
                 <th width="23%">Client</th>
                 <th width="12%">Date</th>
                 <th width="12%">Time Worked</th>
                 <th width="11%">Service Code</th>
                 <th width="11%">Billing State</th>
                 <th colspan="2">Section</th>
            </tr>
          </thead>
          <tbody>
          
          <tr>
            <td><img src="images/pencil_32.png" class="list_icon"></td>
            <td><input type="search" name="client_name"  placeholder="Name"></td>

            <td><input type="date" name="client_date"></td>

            <td><input type="number" name="time_worked" min="0" max="12"></td>

            <td>
              <div class="myselect">
                <select name="serviceCode" class="service_code">
                  <option value="Audit">AUD</option>
                  <option value="Tax Consultancy">TAX</option>
                  <option value="Accountancy">ACC</option>
                  <option value="Administration">ADMIN</option>
                  <option value="Management Consultancy">CON</option>
                  <option value="No Work">IDLE</option>
                  <option value="training Including Self">TRA</option>
                </select>
              </div>
            </td>

            <td>
              <div class="myselect">
                <select name="billable">
                <option>billable</option>
                <option>Non-bllable</option>
              </select>
        </div>
            </td>
            <td width="26%"><input type="search" name="section" placeholder="section"></td>
            <td class="table_icon1"></td>
          </tr>
          <!--
          <tr>
            <td>rfhfh</td>
            <td>rfhfh</td>
            <td>fg</td>
            <td>fgh</td>
            <td>fh</td>
            <td>fth</td>
            <td>thj</td>
            <td class="table_icon"></td>
          </tr>
          <tr>
            <td>rfhfh</td>
            <td>rfhfh</td>
            <td>fg</td>
            <td>fgh</td>
            <td>fh</td>
            <td>fth</td>
            <td>thj</td>
            <td class="table_icon"></td> -->
          </tr>
          
          </tbody>  
    </table>
    <div class="number2">
    <table>
          <thead>
            <tr>
                 <th width="40">Id</th>
                 <th width="279">Client</th>
                 <th width="33">Mon</th>
                 <th width="29">Tue</th>
                 <th width="35">Wed</th>
                 <th width="38">Thur</th>
                 <th width="26">Fri</th>
                 <th width="30">Sat</th>
                 <th width="36">Sun</th>
                 <th width="154">Service Code</th>
                 <th width="122">Billing State</th>
                 <th colspan="2">Section</th>
            </tr>
          </thead>
          <tbody>
          <tr>
            <td>rf</td>
            <td>rf</td>
            <td>fg</td>
            <td>rf</td>
            <td>rf</td>
            <td>fg</td>
            <td>fg</td>
            <td>fgh</td>
            <td>fh</td>
            <td>fth</td>
            <td>thj</td>
            <td width="87">thj</td>
            <td width="40" class="table_icon"></td>
          </tr>      
          </tbody>  
    </table>
  </div>
   <input type="submit" value="Submit TimeSheet" class="ts_button">
   </div>
</div>
	</div>
	<div class="shell_two">
          <div class="sbox">
      <div class="search">
          <input type="search" placeholder="Approved Time Sheets"><input type="submit" value=""> 
      </div>
       sort By: 
          <select class="sort" id="sort_two">
            <option value="date">Date</option>
            <option value="ascending">Ascending</option>
            <option value="descending">Descending</option>
          </select>
      </div>
      <table class="pend_appr">
          <thead>
            <tr>
                 <th>Employee</th>
                 <th>Time Sheet</th>
                 <th>Issue Date</th>
                 <th>Approved By</th>
                 <th>Approval Date</th>
            </tr>
          </thead>
          <tbody>
          <tr>
         <% 
          TimeSheetDataFacade facade = new TimeSheetDataFacade();
          
          List<TimeSheet> sheets  = facade.getApprovedTSheets("01-01-2016","01-12-2016","fill","5");
          
          
          
          
          for(int i=0; i<sheets.size(); i++){ 
          %>
                <td><%= facade.getEmployee(Integer.toString(sheets.get(i).getEmployee_id()) ).getFirstname() %></td>
                <td><%=sheets.get(i).getTimesheet_id() %></td>
                <td><%= sheets.get(i).getDateofcreation() %></td>
                <td><%= facade.getEmployee(Integer.toString(facade.get_EID_From_PIN_MGT(sheets.get(i).getApproval().getPerson_in_mgt_id()))).getFirstname() %></td>
                <td><%=sheets.get(i).getApproval().getDate() %></td>
                <%} %>
          </tr>
          
          </tbody>
      </table>
  </div>
	<div class="shell_three">
          <div class="sbox">
      <div class="search">
          <input type="search" placeholder="Pending Time Sheets"><input type="submit" value=""> 
      </div>
       sort By: 
          <select class="sort" id="sort_three">
            <option value="date">Date</option>
            <option value="ascending">Ascending</option>
            <option value="descending">Descending</option>
          </select>
      </div>
      <table class="pend_appr">
          <thead>
            <tr>
                 <th>Employee</th>
                 <th>Time Sheet</th>
                 <th>Issue Date</th>
            </tr>
          </thead>
          <tbody>
          <% 
         
          
     List<TimeSheet> psheets  = facade.getPendingTSheets("d1", "d2", "filter","5");
           
          for(int i=0; i<psheets.size(); i++){ 
          %>
                <td><%= facade.getEmployee(Integer.toString(psheets.get(i).getEmployee_id()) ).getFirstname() %></td>
                <td><%=psheets.get(i).getTimesheet_id() %></td>
                <td><%= psheets.get(i).getDateofcreation() %></td>
             
                <%} %>  
          </tbody>
      </table>
  </div>
  <div class="shell_four">
     <div class="sbox">
      Staff: 
          <select class="search_by">
          <% 
          TimeSheetDataFacade myFacade = new TimeSheetDataFacade();
          List<Employee> e = myFacade.getAllEmployees();
          
          for(int i=0; i<e.size(); i++){ 
          
          
          %>
            <option value="date"><%=facade.getAllEmployees().get(i).getFirstname() %></option>
            
            <%} %>
            <option value="date">Any</option>
            
          </select>
          <!--
          <div class="search">
            <input type="search" placeholder="Search"><input type="submit" value="" class="report_field"> 
          </div>
          -->
          
          Search By: <!--<input type="date" class="from_date">  To: <input type="date" class="from_date"> -->
       <select class="search_by">
             <option value="date">Client</option>
            <option value="ascending">Code</option>
            <option value="date">Section</option>
             <option value="date">Any</option>
          </select>
            Item: <!--<input type="date" class="from_date">  To: <input type="date" class="from_date"> -->
       <select class="search_by">
             <option value="date">Shell</option>
            <option value="ascending">Nakumatt</option>
            <option value="date">Game</option>
             <option value="date">EBay</option>
             <option value="date">Any</option>
          </select>
          
        
       
      </div> 
        <table class="pend_appr">
          <thead>
            <tr>
            <th></th>
                <th>Client</th>
                 <th>Charge Code</th>
                
                 <th>Mon</th>
                  <th>Tue</th>
                   <th>Wed</th>
                    <th>Thur</th>
                     <th>Fri</th>
                     <th>Sat</th>
                 <th>Total Time</th>
                  
          
                 
            </tr>
          </thead>
          
          <tbody>
     
           
		    <% 
		    
		    PerformanceAnalyzer analyzer = new PerformanceAnalyzer();
   
         List<TimeSheet> reportsheets  = facade.searchTimeSheet("date", "Any","Any","Shell", 1);
		    int timeSheetSize = reportsheets.size();
		    System.out.println("Size "+timeSheetSize);
		    int theVar = 0;
		    
		    
		    //period Total
    	    int totalPeriodMon = 0;
    	    int totalPeriodTue = 0;
    	    int totalPeriodWed = 0;
    	    int totalPeriodThur = 0;
    	    int totalPeriodFri = 0;
    	    int totalPeriodSat = 0;
    	    int totalEmployeePeriodTime =0; 
    	    int weekNo = 1;
    	    java.util.Date newDate = Calendar.getInstance().getTime();
    	    
          for(int i=0; i<reportsheets.size(); i++){ 
        	  
        	  
        	  String fname = facade.getEmployee(Integer.toString(reportsheets.get(i).getEmployee_id())).getFirstname();
        	  String lname = facade.getEmployee(Integer.toString(reportsheets.get(i).getEmployee_id())).getLastname();
        	  String employeename = fname +" "+ lname;
        	  
        	  System.out.println("ID "+reportsheets.get(i).getTimesheet_id());
             
        	    int totalWeekTimeMon = 0;
        	    int totalWeekTimeTue = 0;
        	    int totalWeekTimeWed = 0;
        	    int totalWeekTimeThur = 0;
        	    int totalWeekTimeFri = 0;
        	    int totalWeekTimeSat = 0;
        	    int totalWeekTimeSun = 0;
        	    int totalEmployeeWeekTime = 0;
        	  
        	    
        	  List<DayPlan> plans = reportsheets.get(i).getDayplans();
        	
        	  if(i<timeSheetSize){  //not to print the report table row even without data
        		  
        		 java.sql.Date currentdate = java.sql.Date.valueOf(reportsheets.get(i).getDateofcreation());
        		 int day = currentdate.getDay()+7;
        	     int month = currentdate.getMonth();
        	     int year =currentdate.getYear();
        	     
        	     

        	     if(i==0){    //initiate the new date only once
        	     int initday = day;
        	     newDate.setDate(initday);
        	     newDate.setMonth(month);
        	     newDate.setYear(year);
        	     System.out.println("I shit: "+i);
        	     }
        	     System.out.println("CurrentDate: "+currentdate+" New Date:"+newDate);
        	     
        	     
        	  if(currentdate.getTime() < newDate.getTime()){
        		  System.out.println("yes current date is less new date");
        		  
        	  }
        	  else{
        		  weekNo++;
        		 
        		  newDate.setDate(newDate.getDate()+7);
        		  System.out.println("no current date is more new date");
        		  
        		  
        	  }
        	  
        	  %>
        	   <tr> 
        	      <td> <button ><img src="images/statistics_16.png"> Week <%=weekNo %> Report   </button> <%= employeename %>  </td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                 
        	   </tr>
        	   
        	   <% 
        	  }
        	  int mon = 0;
     		  int tue = 0;
     		  int wed = 0;
     		  int thur = 0;
     		  int fri = 0;
     		  int sat = 0;
     		  
        	  for(int j=0; j<plans.size(); j++){
        		  
        		 mon = plans.get(j).getTimeWorked().getMon();
        		 tue = plans.get(j).getTimeWorked().getTue();
        		 wed = plans.get(j).getTimeWorked().getWed();
        		 thur = plans.get(j).getTimeWorked().getThur();
        		 fri = plans.get(j).getTimeWorked().getFri();
        		 sat = plans.get(j).getTimeWorked().getSat();
        		  
               totalWeekTimeMon += mon;
      	       totalWeekTimeTue += tue;
      	       totalWeekTimeWed += wed;
      	       totalWeekTimeThur += thur;
      	       totalWeekTimeFri += fri;
      	       totalWeekTimeSat += sat;
           
               totalEmployeeWeekTime +=   analyzer.getTotalWeekTime(mon, tue, wed, thur, fri, sat);
        		  %>
        		 
            <tr>
            
                <td><%= plans.get(j).getSection() %></td>
                <td><%= plans.get(j).getClient() %></td>
                <td><%= plans.get(j).getServicecode()  %></td>
                
                <td><%= mon+"  hrs+" %></td>
                <td><%= tue+" hrs-"  %></td>
                <td><%= wed+" hrs+"  %></td>                
                <td><%= thur+" hrs-"  %></td>
                <td><%= fri+" hrs-"  %></td>
                <td><%= sat+" hrs+"  %></td>
                <td><%=  analyzer.getTotalWeekTime(mon, tue, wed, thur, fri, sat) %></td>
             </tr>
               <% }
        	  
        	  
       	      totalPeriodMon += totalWeekTimeMon;
       	      totalPeriodTue += totalWeekTimeTue;                                                                                                        
       	      totalPeriodWed += totalWeekTimeWed;
       	      totalPeriodThur += totalWeekTimeThur;
       	      totalPeriodFri += totalWeekTimeFri;
       	      totalPeriodSat += totalWeekTimeSat;
    	    
        	  totalEmployeePeriodTime += analyzer.getTotalEmployeePeriodTime(totalPeriodMon, totalPeriodTue, totalPeriodWed, totalPeriodThur, totalPeriodFri, totalPeriodSat);
        	  
        	  if(i<timeSheetSize){   // not to print the total table even when theres no data
        	  
        	  %> 
               
               
               <tr>  
                 <td>Week Total</td>
                 <td></td>
                 <td></td>
                 <td><%= totalWeekTimeMon +"hrs"%></td>
                 <td><%= totalWeekTimeTue +"hrs"%></td>
                 <td><%= totalWeekTimeWed +"hrs"%></td>
                 <td><%= totalWeekTimeThur +"hrs"%></td>
                 <td><%= totalWeekTimeFri +"hrs"%></td>
                 <td><%= totalWeekTimeSat +"hrs"%></td>
                 <td><%= totalEmployeeWeekTime +"hrs"%></td>
                 
               </tr>
               <tr><td colspan="10">__________________________________________________________________________________________________________________________________________________________________________________</td>
               
               </tr>
               
               
                <%} //end of if
        	   
                 }
        	    // not to print the period toatl table even when theres no data
            	  
        	     %> 
        	     
        	       <tr></tr>
               
              <tr>
              <% if(timeSheetSize > 0){%> 
              <td>Period Total</td>
                 <td></td>
                 <td></td>
                 <td><%= totalPeriodMon +"hrs"%></td>
                 <td><%= totalPeriodTue +"hrs"%></td>
                 <td><%= totalPeriodWed +"hrs"%></td>
                 <td><%= totalPeriodThur +"hrs"%></td>
                 <td><%= totalPeriodFri +"hrs"%></td>
                 <td><%= totalPeriodSat +"hrs"%></td>
                 <td><%= totalEmployeePeriodTime +"hrs"%></td>
                 <% }
                 
                   else if(timeSheetSize == 0){%> 
              <td></td>
                 <td></td>
                 <td> No Results Found </td>
                 <td></td>
                 <td></td>
                 <td></td>
                 <td></td>
                 <td></td>
                 <td></td>
                 <td></td>
                 <% }%>
                 
                 
                 
               </tr>
        	      
        	     
                
              </tbody>
      </table> 
		   
		   
		   

          
        
      </div>
  </div>
</div>
<div class="message1"></div>
</div>

<div class="sidebar">
  <div class="profile">
      <div class="profileDetails">
          <% 
          TimeSheetDataFacade fac = new TimeSheetDataFacade(); 
          int employee_id = facade.getEID(username, password);
          
          String employeename = fac.getEmployee(Integer.toString(employee_id)).getFirstname();
          String employeeposition = fac.getEmployee(Integer.toString(employee_id)).getPosition_in_co();
          %>
      
          <label><%=  employeename%></label><br>
          <label><%=  employeeposition%></label>
          
      </div>
        <div class="profilePic"><img src="images/office worker1.png" class="p_pic"></div>
      </div>
  <ul class="menu">
      <li class="userAccounts"><a href="#"><img src="images/documents7.png" class="list_icon3">TimeSheets</a>
        <ul>
          <li>New TimeSheet</li>
          <li>Aproved TimeSheets</li>
          <li>Pending TimeSheets</li>
        </ul>
      </li>
      <li class="leave"><a href="#"><img src="images/documents7.png" class="list_icon3">Reports</a></li>
      <li class="leave"><a href="#"><img src="images/stick.png" class="list_icon3">Leave Applications</a></li>
      <li class="yearPlanner"><a href="#"><img src="images/accounts.png" class="list_icon3">Accounts</a>
        <ul>
          <li>My Account</li>
          <li>Employee Accounts</li>
        </ul>
      </li>
      <li class="userAccounts"><a href="#"><img src="images/settings.png" class="list_icon3">Settings</a>
        <ul>
          <li>My Settings</li>
          <li>Company Settings</li>
        </ul>
      </li>
  </ul>
</div>
  

<div class="footer"></div>
</div>
</body>
</html>