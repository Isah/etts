package com.presentation.displaymgt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLoginServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
		//response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String country = request.getParameter("country");
		
		//request.setAttribute("user", username);
		//request.setAttribute("pass", password);
		
		getServletContext().setAttribute("user", username);
		getServletContext().setAttribute("pass", password);
		
		
		UserLoginValidation user = new UserLoginValidation();
		user.setUsername(username);
		user.setPassword(password);
		
		HttpSession session = request.getSession();
		//session.setAttribute("username", username);
		//session.setAttribute("password", password);
		
		if(user.validation()){
			//checking for user type
			if(user.check_userType(username, password).equals("pattern")){
				//HttpSession session = request.getSession();
				session.setAttribute("user", user);
				request.getRequestDispatcher("/timeSheetManager.jsp").forward(request, response);
				
				
			}else{
				//HttpSession session = request.getSession();
				session.setAttribute("user", user);
				request.getRequestDispatcher("/timeSheet_other.jsp").forward(request, response);
			}
			
		}else{
			request.setAttribute("validationMessage", user.getMessage());
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}	

}
