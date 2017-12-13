package classes;
 
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import lib.DbConnection;
 
/**
 *
 * @author Mikael
 */
public class StudentMethods {
   
    
DbConnection dbCode = new DbConnection();
Connection conn = dbCode.Connect();
   
   
    public void printStudents(PrintWriter out) {
        
        String STUDENT  = "<a href='StudentDetail?id=%s'>%s, %s</a>\n"; 
                               
        PreparedStatement getStudents; 
         
         try {
                getStudents = conn.prepareStatement("select * from useraccount where admin is NULL order by ?");
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
       
        //Kombinerer flere tabeller! Må ha useraccount, module og modulbesvarelse tabeller!
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
                rset.beforeFirst(); //Måtte ha med denne, ellers ble den første raden hoppet over!
                while (rset.next()) {   // Move the cursor to the next row, return false if no more row
                  
                   String title = rset.getString("title");
                   String vurdering = rset.getString("vurdering");
                   String m_id = rset.getString("m_id");
                 
                   out.println("<div style=\"module\">ModulID: " +m_id+ " <br>Module navn: "+title+" <br>Vurdering: "+vurdering+"<br></div>"
                   + "<br>");
                   
                    
                    ++rowCount;
                 }  // end while
         } // end try     
         catch (SQLException ex) {
                out.println("Ikke hentet fra DB " +ex);
         }
        
        
        
   }
   
   
   public void addStudent(String id, String firstName, String lastName, String email, String pass, PrintWriter out){

        //name = name;
        String strSelect2 = ("insert into userAccount(firstName, lastName, email, pass) values('"+firstName+"' , '"+lastName+"' ,'"+email+"' , '"+pass+"');");
        
        // System.out.println("The SQL query is: " + strSelect2);
        // out. println("The SQL query is: " + strSelect2);
       
        System.out.println();
        out.println();
 
         try {
            Statement statement = conn.createStatement();
            int rset2 = statement.executeUpdate(strSelect2);
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
   String strSelect3 = ("delete from userAccount where id = '" + arg2 + "'");
   
     // System.out.println("The SQL query is: " + strSelect3);
        // out. println("The SQL query is: " + strSelect3);
       try {
           Statement statement = conn.createStatement();
            int rset3 = statement.executeUpdate(strSelect3);
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
   
   
   
    // Method for user creation where it takes four parameters.
    // The method also takes the four first characters and the first of the lastname, in addition to the last digits
    // of the current year to generate a email which is used to log in to the system.
    public void addUser(String firstName, String lastName, String pass, String email, PrintWriter out){

        DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
        String yearInString = df.format(Calendar.getInstance().getTime());
             
        String firstNamemax = firstName;   
        int maxLengthF = (firstNamemax.length() < 4)?firstNamemax.length():4;
        firstNamemax = firstNamemax.substring(0, maxLengthF);  
        
        String lastNamemax = lastName;   
        int maxLengthL = (lastNamemax.length() < 1)?lastNamemax.length():1;
        lastNamemax = lastNamemax.substring(0, maxLengthL);  
        
        email = firstNamemax.toLowerCase() + lastNamemax.toLowerCase() + yearInString + "@uia.no";
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

    // method for notifying user that creation was done. Takes email as parameter
    public void message(String email, String message, PrintWriter out){

        
        String strSelect3 = ("insert into message(email, message) values('"+email+"', '"+message+"');");
        
        // System.out.println("The SQL query is: " + strSelect2);
        // out. println("The SQL query is: " + strSelect2);
       
        System.out.println();
        out.println();
       // try and catch
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
   
   
   
   

      
   
   
   

