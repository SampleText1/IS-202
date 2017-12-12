package classes;
 
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
   
   
    public void printModules(PrintWriter out) {
        
        String STUDENT  = "<a href='ModuleDetail?id=%s'>%s</a>\n"; 
                               
        PreparedStatement getModules; 
         
         try {
                getModules = conn.prepareStatement("select * from module");
                
                ResultSet rset = getModules.executeQuery();
 
                int rowCount = 0;
                while(rset.next()) {
                    String id = rset.getString("id");
                    String title = rset.getString("title");
                    out.format(STUDENT,id,title);
                    out.println("<br>");
                    
                    ++rowCount;
                 }
                 out.println("<br>Antall moduler = " + rowCount);
         } // end try     
         catch (SQLException ex) {
                out.println("Ikke hentet fra DB " +ex);
         }
         
         
   }
   
   public void skrivEnModul(String id, PrintWriter out)
    {   
       
        //PRØVER Å KOMBINERE FLERE TABELLER
        PreparedStatement getStudents; 
         
         try {
                getStudents = conn.prepareStatement("select * from module where id=?");
                getStudents.setString(1,id);
                
                ResultSet rset = getStudents.executeQuery();
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">"); 
               
                while(rset.next()) {   // Move the cursor to the next row, return false if no more row
                    String idString = rset.getString("id");
                    String title = rset.getString("title");
                    String description   = rset.getString("description");
                    String goals = rset.getString("goals");
                    String resources = rset.getString("resources");
                    String task = rset.getString("task");
                    String deadline = rset.getString("deadline");
                    
                    
                    out.println("<h3>Navn: " + title + "<br> Beskrivelse: " + description + "<br>Mål: " + goals + "<br>Ressurser: " + resources + "<br>Oppgave: " + task + "<br>Frist: " + deadline +"</h3><br>");
                    out.println("<center>\n" + "<form action=\"uploadToDB\" method=\"post\" enctype=\"multipart/form-data\">\n" +
                     "<input type=\"hidden\" name=\"id\" value=" + idString + ">" +
"                    <input type=\"text\" name=\"name\" placeholder=\"Navn\"/>\n" +
"                    <br>\n" +
"                    <input type=\"file\" name=\"file\">\n" +
"                    <input type=\"submit\" value=\"Last opp\">           \n" +
"        </form>" +
"            <input type=\"button\" class=\"abutton\" onclick=\"history.back();\" value=\"Tilbake\">\n" +
"            </center>\n" +
"            <br>");
                    
                   
                 }  // end if
    } // end try     
         catch (SQLException ex) {
                out.println("Ikke hentet fra DB " +ex);
         }
    }
    
   public void addModule(String id, String title, String description, String goals, String task, PrintWriter out){
       this.Connect(out);
        //name = name;
        String strSelect2 = ("insert into module(title, description, goals, task) values('"+title+"' , '"+description+"' ,'"+goals+"' ,'"+task+"');");
       
        // System.out.println("The SQL query is: " + strSelect2);
        // out. println("The SQL query is: " + strSelect2);
       
        System.out.println();
        out.println();
 
         try {
            int rset2 = stmt.executeUpdate(strSelect2);
            conn.commit();
               if (rset2 != 0) {
                    out.println("Modul lagt til<br>" + rset2 );
                    this.printModules(out);
                   
            } else {
                out.println("Inserting record get failure");
            }
       
              } // end try     // end try    
         catch (Exception ex) {
              System.out.println(ex.getCause());
              System.out.println("error");
              out.println("Ikke hentet fra DB " +ex);
         }
         }
   
   
   
   
   
   
   
}
