package classes;
 
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
 
/**
 *
 * @author Mikael
 */
public class ModuleMethods {
   
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
   
   
    public void printStudents(PrintWriter out)
    {
         this.Connect(out);
         String strSelect = "select * from modules";
 
         // System.out.println("The SQL query is: " + strSelect); // Echo For debugging
         // out. println("The SQL query is: " + strSelect);
         
         System.out.println();
         out.println();
 
         try {
             
                ResultSet rset = stmt.executeQuery(strSelect); //En slags iterator
 
                // Step 4: Process the ResultSet by scrolling the cursor forward via next().
                //  For each row, retrieve the contents of the cells with getXxx(columnName).
                // out.println("Studenter i databasen:" +"<br>");
                int rowCount = 0;
                while(rset.next()) {   // Move the cursor to the next row, return false if no more row
                    int id = rset.getInt("id");
                    String title   = rset.getString("title");
                    String description = rset.getString("description");
                    String goals = rset.getString("goals");
                    String resources = rset.getString("resources");
                    String task = rset.getString("task");
                    Date deadline = rset.getDate("deadline");
                    out.println(id + ",  " + title +", " +description+ ", " +goals + ", " +resources+ ", "+task+ ", " +deadline+",<br>");
                    ++rowCount;
                 }  // end while
                 out.println("Total number of records = " + rowCount);
         } // end try    
         catch (SQLException ex) {
                out.println("Ikke hentet fra DB " +ex);
         }
         
         
         
   }
   
   
   public void addModule(String id, String title, String description, String goals, String task, PrintWriter out){
       this.Connect(out);
        //name = name;
        String strSelect2 = ("insert into modules(id, title, description, goals, task) values('"+id+"' , '"+title+"' , '"+description+"' ,'"+goals+"' ,'"+task+"');");
       
        // System.out.println("The SQL query is: " + strSelect2);
        // out. println("The SQL query is: " + strSelect2);
       
        System.out.println();
        out.println();
 
         try {
            int rset2 = stmt.executeUpdate(strSelect2);
            conn.commit();
               if (rset2 != 0) {
                    out.println("Modul lagt til<br>" + rset2 );
                    this.printStudents(out);
                   
            } else {
                out.println("Inserting record get failure");
            }
       
              } // end try    
         catch (Exception ex) {
              System.out.println(ex.getCause());
              System.out.println("error");
              out.println("Ikke hentet fra DB " +ex);
         }
         }
   
   
   
   
   
   
   
}