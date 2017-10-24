package classes;
 
import java.io.PrintWriter;
import java.sql.Connection;
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
public class Code {
   
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
         String strSelect = "select * from userAccount";
 
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
                    int id = rset.getInt("id");
                    String firstName   = rset.getString("firstName");
                    String lastName = rset.getString("lastName");
                    String email = rset.getString("email");
                    String pass = rset.getString("pass");
                    out.println(id + ",  " + firstName +", " +lastName+ ", " +email + ", " +pass+ "<br>");
                    ++rowCount;
                 }  // end while
                 out.println("Total number of records = " + rowCount);
         } // end try    
         catch (SQLException ex) {
                out.println("Ikke hentet fra DB " +ex);
         }
         
         
         
   }
   
   
   public void addStudent(String id, String firstName, String lastName, String email, String pass, PrintWriter out){
       this.Connect(out);
        //name = name;
        String strSelect2 = ("insert into useraccount(firstName, lastName, email, pass) values('"+firstName+"' , '"+lastName+"' ,'"+email+"', '"+pass+"');");
        
        System.out.println("The SQL query is: " + strSelect2);
        out. println("The SQL query is: " + strSelect2);
       
        System.out.println();
        out.println();
 
         try {
            int rset2 = stmt.executeUpdate(strSelect2);
            conn.commit();
               if (rset2 != 0) {
                    out.println("Record has been inserted successfully<br>" + rset2 );
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