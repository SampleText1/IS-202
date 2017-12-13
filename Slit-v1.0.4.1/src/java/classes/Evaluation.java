/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lib.DbConnection;

/**
 *
 * @author Mikael
 */
public class Evaluation {
    
   DbConnection dbCode = new DbConnection();
   Connection conn = dbCode.Connect();
    
    public void listeVurderingsKø(PrintWriter out) {
    
     PreparedStatement ps;   
    
     try {
         
         ps = conn.prepareStatement("select modulbesvarelse.id, vurdering, modulbesvarelse.m_id, modulbesvarelse.s_id\n" +
"from modulbesvarelse join useraccount on modulbesvarelse.s_id=useraccount.id\n" +
"join module on modulbesvarelse.m_id=module.id where vurdering='Ikke godkjent';");
         
         
 ResultSet rset = ps.executeQuery();
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");    
                // Step 4: Process the ResultSet by scrolling the cursor forward via next().
                //  For each row, retrieve the contents of the cells with getXxx(columnName).
                out.println("<h3>Disse modulene må vurderes:" +"<br>");
                
                while(rset.next()) {
                    String id = rset.getString("id");
                    String s_id = rset.getString("s_id");
                    String m_id   = rset.getString("m_id");
                    String vurdering = rset.getString("vurdering");
                    out.println("</div>");
            out.println("<br>");
                    
 out.println("BesvarelseID: " + id + " StundentID: " + s_id + " ModulID: " + m_id + " Vurdering: " + vurdering +"");
 
                   
 out.println("<form action=\"GodkjenningServlet\" method=\"post\">" +
             "<input type=\"Submit\" name=\"id\" value="+id+"> " +       
           " </form> ");
 
 conn.close();
        /*           
        out.println("<center>\n" +
          "<input type=\"submit\" class=\"abutton\" onclick=\"GodkjenningServlet\" value=Vur" +id+ ">\n" +
          "</center>\n");    */
         
      }
    }
     
    catch (SQLException ex) {
                out.println("Ikke hentet fra DB " +ex);
         }
    
    
    
   }
    
    
    
    public void godkjennModul(String id, PrintWriter out) {
        
        PreparedStatement ps;
        
        try {
            String ins = "UPDATE  modulbesvarelse \n" +
"SET vurdering = 'Godkjent' \n" +
"WHERE id = ?";
                    
            ps = conn.prepareStatement(ins);
            
            ps.setString(1, id);
            
            out.println(ps);
            ps.executeUpdate();
            conn.commit();
            conn.close();
            
            
            
        }
        
        catch (SQLException ex) {
                out.println("Ikke hentet fra DB " +ex);
         }
    
    }
            
            
            
}