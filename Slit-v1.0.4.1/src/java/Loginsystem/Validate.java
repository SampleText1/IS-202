package Loginsystem;

import java.sql.*;
import lib.DbConnection;

public class Validate {
    
   DbConnection dbCode = new DbConnection();
   Connection conn = dbCode.Connect();
    
    public boolean checkAdmin(String email,String pass) 
     {
      boolean st =false;
      try{
                Statement statement = conn.createStatement();
                
         PreparedStatement ps =conn.prepareStatement
                             ("select * from userAccount where email=? and pass=? and admin=1");
         ps.setString(1, email);
         ps.setString(2, pass);
         ResultSet rs =ps.executeQuery();
         st = rs.next();
        
      }catch(SQLException e)
      {
      }
         return st;                 
  } 
     public boolean checkUser(String email,String pass) 
     {
      boolean st =false;
      try{
                Statement statement = conn.createStatement();
                
         PreparedStatement ps =conn.prepareStatement
                             ("select * from userAccount where email=? and pass=?");
         ps.setString(1, email);
         ps.setString(2, pass);
         ResultSet rs =ps.executeQuery();
         st = rs.next();
        
      }catch(SQLException e)
      {
      }
         return st;                 
  }  
}