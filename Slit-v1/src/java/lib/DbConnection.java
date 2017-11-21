/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author mariu
 */
public class DbConnection {
    
    Connection conn;        // Must be defined here as class variables, get their value in the login method
    Statement stmt;         //Slik at de forsvinner.
   
   
    public Connection Connect() {
        try {
         // Step 1: Allocate a database 'Connection' object
         Context cont = new InitialContext();
         DataSource ds = (DataSource)cont.lookup("java:comp/env/jdbc/LocalhostDS"); //
         //DataSource ds = (DataSource)cont.lookup("jdbc/LocalhostDS");
         conn =  ds.getConnection();  //Kobler seg til.
         return conn;
         
        }
        catch (SQLException ex ) {
            System.out.println("Not connected to database " +ex);
        }
        catch (NamingException nex) {
            System.out.println("Not correct naming" + nex);
            
            
            
            
        }
        return null;
    }
}
    

