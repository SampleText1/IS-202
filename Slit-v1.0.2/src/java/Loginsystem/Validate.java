package Loginsystem;

import java.sql.*;

public class Validate
 {
    public static boolean checkAdmin(String email,String pass) 
     {
      boolean st =false;
      try{

	 //loading drivers for mysql
         Class.forName("com.mysql.jdbc.Driver");

 	 //creating connection with the database 
         Connection con=DriverManager.getConnection
                        ("jdbc:mysql://localhost:3306/slit","root", "JTQH4490");
         PreparedStatement ps =con.prepareStatement
                             ("select * from userAccount where email=? and pass=? and admin=1");
         ps.setString(1, email);
         ps.setString(2, pass);
         ResultSet rs =ps.executeQuery();
         st = rs.next();
        
      }catch(ClassNotFoundException | SQLException e)
      {
      }
         return st;                 
  } 
     public static boolean checkUser(String email,String pass) 
     {
      boolean st =false;
      try{

	 //loading drivers for mysql
         Class.forName("com.mysql.jdbc.Driver");

 	 //creating connection with the database 
         Connection con=DriverManager.getConnection
                        ("jdbc:mysql://localhost:3306/slit","DITTBRUKERNAVN", "DITTPASSORD");
         PreparedStatement ps =con.prepareStatement
                             ("select * from userAccount where email=? and pass=?");
         ps.setString(1, email);
         ps.setString(2, pass);
         ResultSet rs =ps.executeQuery();
         st = rs.next();
        
      }catch(ClassNotFoundException | SQLException e)
      {
      }
         return st;                 
  }   
}
