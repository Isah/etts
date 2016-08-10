package com.presentation.displaymgt;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.scheduler.Schedule;
import com.application.staff.Address;
import com.application.staff.Employee;
import com.data.dataFacade.ProfileManager;

public class UserSignupServlet extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
		
		String first_name = request.getParameter("firstname");
		String last_name = request.getParameter("lastname");
		String username = request.getParameter("username");
		//date
	    
		
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		int daydate = Integer.parseInt(request.getParameter("date"));
		
		
		Calendar.getInstance().set(year, month, daydate);
		Date date = Calendar.getInstance().getTime();
		
		Schedule schedule = new Schedule();
		String dob = schedule.convertToSqlDate(date);
		
		
		String m_status = request.getParameter("m_status");
		String position_co = request.getParameter("pos_company");
		String datejoined = request.getParameter("date_joined");
		int billing_rate = Integer.parseInt(request.getParameter("bill_state"));
		int employee_no = Integer.parseInt(request.getParameter("emplo_number"));
		int id_number = Integer.parseInt(request.getParameter("id_number"));
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String phy_address= request.getParameter("phy_address");
		String kin_name = request.getParameter("kin_name");
		String kin_address = request.getParameter("kin_address");
		String password = request.getParameter("password");
		String location = request.getParameter("location");
		String city = request.getParameter("city");
		
		
		Employee employee = new Employee();
		
		Address address = new Address();
		address.setCity(city);
		address.setCountry(location);
		address.setEmail(email);
		address.setKinemail(kin_address);
		address.setTel_no(phone);
		employee.setAddress(address);
		
		
		employee.setBilling_rate(billing_rate);
		employee.setDate_joined_co(datejoined);
		employee.setDob(dob);
		employee.setEmployee_no(employee_no);
		employee.setFirstname(first_name);
		employee.setIdno(id_number);
		employee.setLastname(last_name);
		employee.setMarital_status(m_status);
		employee.setNext_of_kin(kin_name);
		employee.setPosition_in_co(position_co);
		employee.setUsername(username);
		employee.setPassword(password);  
		
		ProfileManager pmanager = new ProfileManager();
		//pmanager.signUp(employee, position_co);
		
		if(pmanager.signUp(employee, position_co)){
			request.getRequestDispatcher("/timeSheetManager.jsp").forward(request, response);;
		}else if(!pmanager.signUp(employee, position_co)){
			request.getRequestDispatcher("/signup.jsp").forward(request, response);
			request.setAttribute("error_message", "signup failed");
		}	
		
		
	}
}
