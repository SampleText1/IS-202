package classes;
 
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import javax.servlet.http.Part;
import lib.DbConnection;
 
/**
 *
 * @author Marius
 */
public class UploadMethods {
   
      DbConnection dbCode = new DbConnection();
   Connection conn = dbCode.Connect();
   
   
    
   
   public void addMethod(String first_name, String last_name, Part filePart, PrintWriter out){
       
        //name = name;
        String strSelect2 = ("insert into uploads(first_name, last_name, photo) values('"+first_name+"' , '"+last_name+"' ,'"+filePart+"');");
        
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
                    System.out.println("kult");
                    
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
