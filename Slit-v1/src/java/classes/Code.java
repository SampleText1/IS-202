package classes;
 
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
public class Code {
   
    Connection conn;        // Must be defined here as class variables, get their value in the login method
    Statement stmt;         //Slik at de forsvinner.
   
   
    public void Connect(PrintWriter out) {
        try {
         // Step 1: Allocate a database 'Connection' object
         Context cont = new InitialContext(); //ET INTERFACE SOM ER INNGANG TIL TOMEE
         DataSource ds = (DataSource)cont.lookup("java:comp/env/jdbc/LocalhostDS"); //SLÅR OPP NOE(ADRESSE) I "KATALOGEN"?
         //DataSource ds = (DataSource)cont.lookup("jdbc/LocalhostDS"); //OPPRETTER FLERE DATASOURCES I TOMME.XML FOR FLERE DATABASER.
         conn =  ds.getConnection();  //Kobler seg til.
 
         // Step 2: Allocate a 'Statement' object in the Connection
         stmt = conn.createStatement(); //Kobling og Cohesion... Burde opprette statement for hver ting? Flere kan tulle med hverandre.
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
 
         // System.out.println("The SQL query is: " + strSelect); // Echo For debugging
         // out. println("The SQL query is: " + strSelect);
         
         System.out.println();
         out.println();
 
         try {
             
                //BURDE OPPRETTE RESULTSETTET HER!!!! DEN SOM ER I CONNECT METODEN!!
               ResultSet rset = stmt.executeQuery(strSelect); //En slags iterator
 
                // Step 4: Process the ResultSet by scrolling the cursor forward via next().
                //  For each row, retrieve the contents of the cells with getXxx(columnName).
                // out.println("Studenter i databasen:" +"<br>");
                int rowCount = 0; //DENNE MÅ IKKE VÆRE MED! MEN KJEKT Å KUNNE BRUKE FOR Å VITE HVOR MANGE ETC.
                while(rset.next()) {   // Move the cursor to the next row, return false if no more row//rset.next() må hvertfall kalle på 1 rad i tabellen!
                    int id = rset.getInt("id");
                    String firstName   = rset.getString("firstName"); //RSET ER STRING.
                    String lastName = rset.getString("lastName");
                    String email = rset.getString("email");
                    String pass = rset.getString("pass");
                    out.println(id + ",  " + firstName +", " +lastName+ ", " +email + ", " +pass+ "<br>");
                    //out.format(STUDENT, id, firstName, lastName) <-- Dette ble bestem når man opprettet STUDENT referansen. Da sa man at man ville ha 3 paramaetre (%s ene)
                    ++rowCount;
                 }  // end while. Kan kune gå framover?! Laget et studentobjekt med Liste hvis man vil gjøre mer.
                 out.println("Total number of records = " + rowCount);
         } // end try    
         catch (SQLException ex) {
                out.println("Ikke hentet fra DB " +ex);
         }
         
         
         
   }
   
    
 
    
   public void addStudent(String id, String firstName, String lastName, String email, String pass, PrintWriter out){
       this.Connect(out);
       
       //stmt = conn.createStatement();
       
        //name = name;
        
      /**  //Kan legge til String.format("insert into.....);
        String strSelect2 = ("insert into useraccount(firstName, lastName, email, pass) values('"+firstName+"' , '"+lastName+"' ,'"+email+"' ,'"+pass+"');");
        
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
              out.println("Ikke lagret i DB " +ex);
         } */
      
        PreparedStatement newStud;
   
        try {            
                newStud = conn.prepareStatement("insert into useraccount(firstName, lastName, email, pass) values (?,?,?,?)");
                //newStud.setString(1, id); //Begynner på 1, ikke 0. Samme gjelder resultSet.
                newStud.setString(1,firstName);
                newStud.setString(2,lastName);
                newStud.setString(3,email);
                newStud.setString(4,pass);
        
                newStud.executeUpdate();
                out.println("Student har blit lagt til:<br>" + newStud);
        }
        //end try
            catch (SQLException ex) {
                out.println("Ikke lagret i DB " + ex);
        
                    }
      
         
        }
   
   public void deleteStudent (String arg1, String arg2, PrintWriter out){
       this.Connect(out);

       String strSelect3 = ("delete from useraccount where " + arg1 + " = '" + arg2 + "'");
         System.out.println("The SQL query is: " + strSelect3);
         out. println("The SQL query is: " + strSelect3);
       try {
            int rset3 = stmt.executeUpdate(strSelect3);
            conn.commit();
               if (rset3 != 0) {
                    out.println("Record has been inserted successfully<br>" + rset3 );
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
   
   public void commit() {
         
             try {           
             conn.commit();
         } // end try     
          
             catch (SQLException ex) {
                System.out.println("Ikke close DB " +ex);
                    
        }
                
        }
   
}
   
   
   
   
   
