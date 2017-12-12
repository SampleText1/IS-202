package classes;
 
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
 
/**
 *
 * @author Mikael
 */
public class StudentMethods {
   
      Connection conn;        // Must be defined here as class variables, get their value in the login method
    Statement stmt;        
   
   
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
   
   
    public void printStudents(PrintWriter out) {
        
        String STUDENT  = "<a href='StudentDetail?id=%s'>%s, %s</a>\n"; 
                               
        PreparedStatement getStudents; 
         
         try {
                getStudents = conn.prepareStatement("select * from useraccount order by ?");
                getStudents.setString(1,"lastName");
                
                ResultSet rset = getStudents.executeQuery();
 
                int rowCount = 0;
                while(rset.next()) {   // Move the cursor to the next row, return false if no more row
                    String id = rset.getString("id");
                    String firstName = rset.getString("firstName");
                    String lastName   = rset.getString("lastName");
                    out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
                    out.println("<div class=stulist>");
                    out.format(STUDENT, id, lastName, firstName);
                    out.println("</div>");
                    out.println("<br>");
                    
                    ++rowCount;
                 }  // end while
                 out.println("<br>");
                 out.println("Antall elever = " + rowCount);
         } // end try     
         catch (SQLException ex) {
                out.println("Ikke hentet fra DB " +ex);
         }
         
   }
    
    
    public void skrivEnStudent(String id, PrintWriter out)
    {   
       
        //Tries to combine multible tables
        PreparedStatement getStudents; 
         
         try {
                getStudents = conn.prepareStatement("select * from modulbesvarelse\n" +
"join useraccount\n" +
"			on modulbesvarelse.s_id=useraccount.id\n" +
"join module\n" +
"			on modulbesvarelse.m_id=module.id\n" +
"where modulbesvarelse.s_id=?;");
                getStudents.setString(1,id);
                
                ResultSet rset = getStudents.executeQuery();
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");    
                // Step 4: Process the ResultSet by scrolling the cursor forward via next().
                //  For each row, retrieve the contents of the cells with getXxx(columnName).
                out.println("<h3>Valgt elev er:" +"<br>");
                
                if (rset.next()) {
                    String idString = rset.getString("s_id");
                    String firstName = rset.getString("firstName");
                    String lastName   = rset.getString("lastName");
                    String email = rset.getString("email");
                    
                    out.println("StudentID: " +idString + "<br> Fornavn: " + firstName + "<br> Etternavn: " + lastName +"<br> Email: "+email+"<br>");
                   
                   out.format("<br>Moduler:" +"<br>");  
                    
                 }  // end if
                
                int rowCount = 0;
                rset.beforeFirst(); //Used so that the first row does´nt get skipped
                while (rset.next()) {   // Move the cursor to the next row, return false if no more row
                  
                   String title = rset.getString("title");
                   String vurdering = rset.getString("vurdering");
                   String m_id = rset.getString("m_id");
                 
                  out.println("ModulID: " +m_id+ " Module navn: "+title+" Vurdering: "+vurdering+"<br></h3>");
                  
                  out.println("<center>\n" +
"            <input type=\"button\" class=\"abutton\" onclick=\"history.back();\" value=\"Tilbake\">\n" +
"            </center>\n" +
"            <br>");
                   
                    
                    ++rowCount;
                 }  // end while
         } // end try     
         catch (SQLException ex) {
                out.println("Ikke hentet fra DB " +ex);
         }
        
        
        
   }
   
   
   public void addStudent(String id, String firstName, String lastName, String email, String pass, PrintWriter out){
       this.Connect(out);
        //name = name;
        String strSelect2 = ("insert into userAccount(firstName, lastName, email, pass) values('"+firstName+"' , '"+lastName+"' ,'"+email+"' , '"+pass+"');");
        
        // System.out.println("The SQL query is: " + strSelect2);
        // out. println("The SQL query is: " + strSelect2);
       
        System.out.println();
        out.println();
 
         try {
            int rset2 = stmt.executeUpdate(strSelect2);
            conn.commit();
               if (rset2 != 0) {
                    //out.println(rset2);
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
   
   
   public void deleteStudent(String arg1, String arg2, PrintWriter out){
   this.Connect(out);
   String strSelect3 = ("delete from userAccount where id = '" + arg2 + "'");
   
     // System.out.println("The SQL query is: " + strSelect3);
        // out. println("The SQL query is: " + strSelect3);
       try {
            int rset3 = stmt.executeUpdate(strSelect3);
            conn.commit();
               if (rset3 != 0) {
                    // out.println("Record has been inserted successfully<br>" + rset3 );
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
   
   
   
   
    public void addUser(String firstName, String lastName, String pass, String email, PrintWriter out){
       
        //name = name;
        Calendar now = Calendar.getInstance();
             int year = now.get(Calendar.YEAR);
             String yearInString = String.valueOf(year);
        email = firstName + yearInString + "@uia.no";
        String strSelect3 = ("insert into userAccount(firstName, lastName, pass, email) values('"+firstName+"' , '"+lastName+"' , '"+pass+"' , '"+email+"');");
        
        // System.out.println("The SQL query is: " + strSelect2);
        // out. println("The SQL query is: " + strSelect2);
       
        System.out.println();
        out.println();
 
         try {
             Statement statement = conn.createStatement();
            int rset3 = statement.executeUpdate(strSelect3);
            conn.commit();
               if (rset3 != 0) {
                    //out.println(rset2);
                    System.out.println("Bruker laget");
                    
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
    
   
   
   
   
   

      
   
   
   

