package com.data.profiledatamgt;

import java.sql.SQLException;

import com.application.staff.Address;
import com.application.staff.Employee;
import com.data.dataFacade.DatabaseConfig;
import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.PreparedStatement;
import com.google.cloud.sql.jdbc.ResultSet;
import com.google.cloud.sql.jdbc.Statement;

public class SignUpImpl implements SignUpDAO{

	
     Connection conn = null;
	
	public SignUpImpl(){
		
		 conn = DatabaseConfig.getMySqlInstance().connect();

		
	}
	
	
	@Override
	public int getEmpID(String username, String pass) {
		// TODO Auto-generated method stub
	
		int a = 0;
		
		String q = "select employee_id from login where user_name = '"+username+"' AND password = '"+pass+"' ";
		Statement st;
		try {
			st = conn.createStatement();
			
			ResultSet rset = st.executeQuery(q);
			
			while(rset.next()){
				
			a = rset.getInt("employee_id");
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return a;
	}


	@Override
	public boolean isUserValid(String username, String pass) {

      boolean value = false;
      
         String query = "select count(*) from login where user_name = '"+username+"'  AND password = '"+pass+"'";
      
		int n = DatabaseConfig.getMySqlInstance().getResultSetInt(query, conn);
		
		if(n>0){
			
			value = true;
		}
		else{
			
			value = false;
		}
		
		return value;
	}


	@Override
	public boolean registerUser(Employee employee,String user) {
	
		int count1,count2,count3,count4,count5,count6 = 0;
		int count7 =0;
		int count8 =0;
		int count9 = 0;
		int count20 = 0;
		
		boolean isRegister = false;
		
		
		String firstname = employee.getFirstname();
		String lastname = employee.getLastname();
		String dob = employee.getDob();
		String leavedate = employee.getLeavedate();
		String maritalstatus = employee.getMarital_status();
		String position_co = employee.getPosition_in_co();
		int no_dependents = employee.getNumber_of_dependents();
		String nextofKin = employee.getNext_of_kin();
		int billingrate = employee.getBilling_rate();
		String datejoinedco = employee.getDate_joined_co();
		int employeeno = employee.getEmployee_no();
		int idno = employee.getIdno();
		    Address address = employee.getAddress();
		    String city = address.getCity();
		    String country = address.getCountry();
		    String email = address.getEmail();
		    String kinemail = address.getKinemail();
		    String telno = address.getTel_no();
		

		//1.insert into employye,address,telephone                                                                                                                                                         //first_name,last_name,dob,marital_status,id_no,employee_no,billing_rate,position_in_co,no_of_dependents,next_of_kin,date_joined_co,leave_date
		String employeeSql = "insert into employee (first_name,last_name,dob,marital_status,id_no,employee_no,billing_rate,position_in_co,no_of_dependents,next_of_kin,date_joined_co,leave_date) values ('"+firstname+"','"+lastname+"','"+dob+"','"+maritalstatus+"','"+idno+"','"+employeeno+"','"+billingrate+"','"+position_co+"','"+no_dependents+"','"+nextofKin+"','"+datejoinedco+"','"+leavedate+"')   ";
		String addressSql = "insert into address (employee_id,email,country,city,next_of_kin_email) values ((select max(employee_id) from employee), '"+email+"' ,'"+country+"', '"+city+"','"+kinemail+"')";
		String loginSql  = "insert into login (user_name,password,employee_id) values ('"+employee.getUsername()+"', '"+employee.getPassword()+"',(select max(employee_id) from employee)) ";
		//String personalAddrSql  = "insert into address (address_id) values ((select max(address_id) from address)) ";
		String telSql = "insert into telephone (tel_no,address_id) values ('"+telno+"', (select max(address_id) from address))";
		//insert for auditor,admin    patner,manager
		
		try {
			PreparedStatement employeeStatement = conn.prepareStatement( employeeSql);
			count1  = employeeStatement.executeUpdate();
			PreparedStatement addressStatement = conn.prepareStatement(addressSql);
			count2 = addressStatement.executeUpdate();
			PreparedStatement telStatement = conn.prepareStatement(telSql);
			count3  = telStatement.executeUpdate();
			PreparedStatement loginStatement = conn.prepareStatement(loginSql);
			loginStatement.executeUpdate();
		    
		
		
		String staffSql = "insert into staff (employee_id) values ((select max(employee_id) from employee))";
		String personMgtSql = "insert into person_in_mgt (employee_id) values ( (select max(employee_id) from employee ))";
		
		PreparedStatement staffStatement = conn.prepareStatement(staffSql);
		count4 = staffStatement.executeUpdate();
		PreparedStatement personmgtStatement = conn.prepareStatement(personMgtSql);
		count5 = personmgtStatement.executeUpdate();
		
		String userSql ="";
		String auditor = "";
		
		
		int validator = 0;
		
		
		
		if(user.equals("SeniorAuditor")){
			auditor = "senior";
			
			userSql = "insert into auditor (staff_id,type) values ((select max(staff_id) from staff),'"+auditor+"')";
			PreparedStatement auditorStatement = conn.prepareStatement(userSql);
			validator = auditorStatement.executeUpdate();
		}   
		
		
		else if(user.equals("AssistantAuditor")){
			auditor = "assistant";
			
			userSql = "insert into auditor (staff_id,type) values ((select max(staff_id) from staff),'"+auditor+"')";
			PreparedStatement auditorStatement = conn.prepareStatement(userSql);
			validator = auditorStatement.executeUpdate();
		}
		
		else if(user.equals("Administrator")){
			userSql = "insert into admin (staff_id) values ((select max(staff_id) from staff))";
			PreparedStatement adminStatement = conn.prepareStatement(userSql);
			validator = adminStatement.executeUpdate();
		}
       else if(user.equals("Partner")){
			
    	   userSql = "insert into patner (person_in_mgt_id) values ((select max(person_in_mgt_id) from person_in_mgt))";
    	   PreparedStatement patnerStatement = conn.prepareStatement(userSql);
    	   validator = patnerStatement.executeUpdate();
       }
       else if(user.equals("Manager")){
			
    	   userSql = "insert into manager (person_in_mgt_id) values ((select max(person_in_mgt_id) from person_in_mgt))";
    	   PreparedStatement managerStatement = conn.prepareStatement(userSql);
    	   validator = managerStatement.executeUpdate();
		}
		
		
		
		if(validator >0){
			
			isRegister = true;
			
		}
		else{
			
			isRegister = false;
			
		}
		
		
		
		
		
		} 
        catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		
		
		
		return isRegister;
	}


	@Override
	public Employee getEmployee(String employees_no) {
		// TODO Auto-generated method stub
	
         Employee employee = null;
         
		 String employeeAddrjoinSQl = "SELECT a.email,a.country,a.city, e.first_name,e.last_name,e.dob,e.marital_status,e.idno"
		 		+ "e.employee_no,e.billing_rate,e.position_in_co,e.no_of_dependents,e.next_of_kin,e.date_joined_co,e.leave_date "
		 		+ "FROM address as a INNER JOIN employee as e ON a.employee_id = e.employee_id where a.employee_id = (select employee_id from employee where employee_no = '"+employees_no+"')"   ;  

		    
		    
		 try {
			Statement statement = conn.createStatement();
		    ResultSet rs = statement.executeQuery(employeeAddrjoinSQl);
		    while(rs.next()){
		    	
		    	String firstname = rs.getString("first_name");
				String lastname = rs.getString("last_name");
				String dob = rs.getString("dob");
				String leavedate = rs.getString("leave_date");
				String maritalstatus = rs.getString("marital_status");
				String position_co = rs.getString("position_in_co");
				int no_dependents = rs.getInt("no_of_dependents");
				String nextofKin = rs.getString("next_of_Kin");
				int billingrate = rs.getInt("billing_rate");;
				String datejoinedco = rs.getString("date_joined_co");
				int employee_no = rs.getInt("employee_no");
				int idno = rs.getInt("id_no");
				   // Address address = employee.getAddress();
				    String city = rs.getString("city");
				    String country = rs.getString("country");
				    String email = rs.getString("email");
				   
				   // String telno = rs.getString("tel_no");
				
		     Address address = new Address();
		     
		     address.setCity(city);
		     address.setCountry(country);
		     address.setEmail(email);
		     address.setTel_no("0758591842");          ///not yet resolved
		     
		     employee = new Employee();
		     
		     employee.setAddress(address);
		     employee.setBilling_rate(billingrate);
		     employee.setDate_joined_co(datejoinedco);
		     employee.setDob(dob);    
		     employee.setEmployee_no(employee_no);
		     employee.setFirstname(firstname);
		     employee.setIdno(idno);
		     employee.setLastname(lastname);
		     employee.setLeavedate(leavedate);
		     employee.setMarital_status(maritalstatus);
		     employee.setNext_of_kin(nextofKin);
		     employee.setNumber_of_dependents(no_dependents);
		     employee.setPosition_in_co(position_co);
		     
		     
		    }

		
		 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		

		return employee;
	}


	@Override
	public boolean isUserNameTaken(String username) {
		
		boolean isTaken = false;
		
		
		String query = "select count(username) from login where username like '"+username+"' ";
		
		int value = 0;
		
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rset = st.executeQuery(query);
			
			while(rset.next()){
				
				value = rset.getInt(1);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		if(value > 0){
			isTaken =  true;
			
		}
		else if(value == 0){
			
			isTaken =  false;
			
		}
		
		return isTaken;
	}


	@Override
	public boolean isEmailExist(String email) {
		
	boolean isExist = false;
		
		
		String query = "select count(email) from address where email like '"+email+"' ";
		
		int value = 0;
		
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rset = st.executeQuery(query);
			
			while(rset.next()){
				
				value = rset.getInt(1);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		if(value > 0){
			isExist =  true;
			
		}
		else if(value == 0){
			
			isExist =  false;
			
		}
		
		return isExist;
	}


	@Override
	public boolean isEmployeeNoExist(String employeeno) {

		boolean isExist = false;
		int value = 0;
		
       String query = "select count(employee_no) from employee where employee_no like '"+employeeno+"'";
       
       try {
		Statement st = conn.createStatement();
		ResultSet rset = st.executeQuery(query);
		
		while(rset.next()){
			
			
			value = rset.getInt(1);
			
		}
	
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
		if(value >0){
			
			isExist = true;
			
		}
		else{
			isExist = false;
			
			
		}

		return isExist;
	}

		
}
