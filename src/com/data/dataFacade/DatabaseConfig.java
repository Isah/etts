package com.data.dataFacade;




import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */






import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.ResultSet;
import com.google.cloud.sql.jdbc.Statement;

/**
 *
 * @author Tzur
 */
public class DatabaseConfig {
  
               //Singleton Pattern in action
    private static DatabaseConfig dbaccess;   // hold our one instance
    
    private DatabaseConfig(){}   // only DBFunctions can instantiate the class
    
    public static DatabaseConfig getMySqlInstance(){   /// you can use this method to access its instance and create it;
    
        if(dbaccess == null){  // lazy intantiation, if its null create it
            
            dbaccess = new DatabaseConfig();
            
        }
      return dbaccess;    //if its not null it was created so just return it;
    
    }
    
   Connection connection = null; 
   Statement statement = null;
   ResultSet rs;
   
   public  Connection connect(){
	   Connection connection = null; 
        try {
            Class.forName("com.mysql.jdbc.Driver");
           // String url = "jdbc:mysql://localhost:3306/"+db;
            String url = "jdbc:google:rdbms://localhost:3306";
        //    connection = (Connection) DriverManager.getConnection(url, "root","");
            connection = (Connection) DriverManager.getConnection("jdbc:google:rdbms://localhost:3306/root");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
     return connection;   
} 
    
    
    
  public void close(){
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConfig.class.getName()).log(Level.SEVERE, null, ex);
        }


}
    
  public List getList(String query, Connection conn) {
    ResultSet rset;
    List list = new ArrayList();
   
            try {
                   Statement st = conn.createStatement();
                   rset =  st.executeQuery(query);
           
                
                
                while(rset.next())
                {           
                    
                    list.add(rset.getString(1));
                    
                        
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConfig.class.getName()).log(Level.SEVERE, null, ex);
            }
              
       
        
             return  list;
    }
 
    
public int getResultSetInt(String query, Connection conn) {
    
      int r =0;
 
   
            try {
                   Statement st = conn.createStatement();
                   rs =  st.executeQuery(query);
           
                
                
                while(rs.next())
                {
                    
                    r =   rs.getInt(1);
                    
                    
                    
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConfig.class.getName()).log(Level.SEVERE, null, ex);
            }
              
       
        
             return  r;
    }

public String getName(String query, Connection conn,String var) {
    
     String name ="";
               try {
                
                 
                   ResultSet rs =  conn.createStatement().executeQuery(query);
           
               while(rs.next())
                {
                    
                    name = rs.getString(var);
                }
                
            } catch (SQLException ex) {
                name = ex.toString();
             
            }
            return  name;
    }


public String getMobile(String query, Connection conn) {
    
     String name ="";
               try {
                
                 Statement st = conn.createStatement();
                   rs =  st.executeQuery(query);
           
               while(rs.next())
                {
                    
                    name = rs.getString("customer_mobile");
                }
                
            } catch (SQLException ex) {
                name = ex.toString();
               // Logger.getLogger(DatabaseAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
            return  name;
    }

      
    
}
