package classes;
 
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
   
  //    Connection conn;        // Must be defined here as class variables, get their value in the login method
   //Statement stmt;         //Slik at de forsvinner.
   
   DbConnection dbCode = new DbConnection();
   Connection conn = dbCode.Connect();
    /*
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
    }*/
   
   
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
       //this.Connect(out);
        //name = name;
        PreparedStatement addStudent;
        out.println("Test addStudenet");
        
        //String strSelect2 = ("insert into userAccount(firstName, lastName, email, pass) values('"+firstName+"' , '"+lastName+"' ,'"+email+"' , '"+pass+"');");
        
        // System.out.println("The SQL query is: " + strSelect2);
        // out. println("The SQL query is: " + strSelect2);
       
        System.out.println();
        out.println();
 
         try {
            String ins = "insert into useraccount (firstName,lastName,email,pass) values(?,?,?,?)";
            
            addStudent = conn.prepareStatement(ins);
            
            
            addStudent.setString(1,firstName);
            addStudent.setString(2,lastName);
            addStudent.setString(3,email);
            addStudent.setString(4,pass);
            
            out.println(addStudent);
            out.println("Er det her det er feil?");
            addStudent.executeUpdate();
            conn.commit();
            conn.close();
           
            out.println("Lagt til " +firstName + " " +lastName+ " " +email+ " " +pass+ "");
                    
        
       
              } // end try    
         catch (Exception ex) {
              System.out.println(ex.getCause());
              System.out.println("error");
              out.println("Ikke hentet fra DB " +ex);
         }
   }
   
   
   public void deleteStudent(String id, String firstName, String lastName, PrintWriter out){
   //this.Connect(out);
   
  
   
   PreparedStatement deleteStudent;
   out.println("DeleteStudent metoden");
   
   
   
     // System.out.println("The SQL query is: " + strSelect3);
        // out. println("The SQL query is: " + strSelect3);
       try {
           
            String ins = "delete from userAccount where id = ?";
            
            deleteStudent = conn.prepareStatement(ins);
             
            deleteStudent.setString(1, id);
            deleteStudent.executeUpdate();
            out.println("Student fjernet fra listen...");
            
            conn.commit();
            conn.close();
           
       
              } // end try    
         catch (Exception ex) {
              System.out.println(ex.getCause());
              System.out.println("error");
              out.println("Ikke hentet fra DB " +ex);
      
       
   }
   
   
 } 
   
   
}
