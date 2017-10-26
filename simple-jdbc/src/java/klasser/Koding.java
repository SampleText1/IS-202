/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasser;

import java.io.PrintWriter;
import java.sql.*; 
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 *
 * @author Mikael
 */
public class Koding {
    
    Connection conn;        // Must be defined here as class variables, get their value in the login method
    Statement stmt;         //Slik at de forsvinner.
    
    
    public void Connect(PrintWriter out) {
        try {
         // Step 1: Allocate a database 'Connection' object
         Context cont = new InitialContext();
         DataSource ds = (DataSource)cont.lookup("java:comp/env/jdbc/LocalhostDS"); // 
         //DataSource ds = (DataSource)cont.lookup("jdbc/LocalhostDS");
         conn =  ds.getConnection();  //Kobler seg til.
 
         // Step 2: Allocate a 'Statement' object in the Connection
         stmt = conn.createStatement();
        }
        catch (SQLException ex ) {
            out.println("Not connected to database " +ex);
        }
        catch (NamingException nex) {
            out.println("Not correct naming" + nex);
        }
    }
    
    
    public void skrivStudenter(PrintWriter out)
    { 
         String strSelect = "select * from person";

         System.out.println("The SQL query is: " + strSelect); // Echo For debugging
         out. println("The SQL query is: " + strSelect);
         
         System.out.println();
         out.println();
 
         try {
             
                ResultSet rset = stmt.executeQuery(strSelect); //En slags iterator
 
                // Step 4: Process the ResultSet by scrolling the cursor forward via next().
                //  For each row, retrieve the contents of the cells with getXxx(columnName).
                out.println("The records selected are:" +"<br>");
                int rowCount = 0;
                while(rset.next()) {   // Move the cursor to the next row, return false if no more row
                    Long id = rset.getLong("id");
                    String name   = rset.getString("name");
                    out.println(id + ",  " + name +"<br>");
                    ++rowCount;
                 }  // end while
                 out.println("Total number of records = " + rowCount);
         } // end try     
         catch (SQLException ex) {
                out.println("Ikke hentet fra DB " +ex);
         }
         
         
         
   }
    
    
   public void addStudent(Long id, String name, PrintWriter out){
       
        //name = name;
        String strSelect2 = ("insert into person values('"+id+"' , '"+name+"')");
        
        System.out.println("The SQL query is: " + strSelect2);
        out. println("The SQL query is: " + strSelect2);
        
        System.out.println();
        out.println();
 
         try {
             
            //    ResultSet rset = stmt.executeQuery(strSelect2);
                
        int rset2 = stmt.executeUpdate(strSelect2);
           if (rset2 != 0) {
        out.println("Record has been inserted successfully<br>");
        } else {
            out.println("Inserting record get failure");
        }
        
              } // end try     
         catch (SQLException ex) {
              ex.printStackTrace();
                //out.println("Ikke hentet fra DB " +ex);
         }
         }
    
    
    
    
    
}
