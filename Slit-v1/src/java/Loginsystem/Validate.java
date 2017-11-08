package Loginsystem;

import java.sql.*;

public class Validate
 {
<<<<<<< HEAD
=======
    public static boolean checkAdmin(String email,String pass) 
     {
      boolean st =false;
      try{

	 //loading drivers for mysql
         Class.forName("org.mariadb.jdbc.Driver");

 	 //creating connection with the database 
         Connection con=DriverManager.getConnection
                        ("jdbc:mariadb://localhost:3306/slit","Vegard", "");
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
>>>>>>> Vegard
     public static boolean checkUser(String email,String pass) 
     {
      boolean st =false;
      try{

	 //loading drivers for mysql
<<<<<<< HEAD
         Class.forName("com.mysql.jdbc.Driver");

 	 //creating connection with the database 
         Connection con=DriverManager.getConnection
                        ("jdbc:mysql://localhost:3306/slit","Vegard", "gunners14");
=======
         Class.forName("org.mariadb.jdbc.Driver");

 	 //creating connection with the database 
         Connection con=DriverManager.getConnection
                        ("jdbc:mariadb://localhost:3306/slit","Vegard", "");
>>>>>>> Vegard
         PreparedStatement ps =con.prepareStatement
                             ("select * from userAccount where email=? and pass=?");
         ps.setString(1, email);
         ps.setString(2, pass);
         ResultSet rs =ps.executeQuery();
         st = rs.next();
        
<<<<<<< HEAD
      }catch(Exception e)
      {
          e.printStackTrace();
=======
      }catch(ClassNotFoundException | SQLException e)
      {
>>>>>>> Vegard
      }
         return st;                 
  }   
}