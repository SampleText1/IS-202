package servlets;
 
import classes.StudentMethods;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lib.DbConnection;
 

//Use of annotation
@WebServlet(name = "RegisterUserServlet", urlPatterns = {"/RegisterUserServlet"})
public class RegisterUserServlet extends HttpServlet {
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
         throws ServletException, IOException {
         try (PrintWriter out = response.getWriter()) {
           // parameterrequest from the html file
         String FirstName = request.getParameter("firstName");
         String LastName = request.getParameter("lastName");
         String pass = request.getParameter("pass");
         String email = request.getParameter ("email");
         
        DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
        String yearInString = df.format(Calendar.getInstance().getTime());
             
        String firstNamemax = FirstName;   
        int maxLengthF = (firstNamemax.length() < 4)?firstNamemax.length():4;
        firstNamemax = firstNamemax.substring(0, maxLengthF);  
        
        String lastNamemax = LastName;   
        int maxLengthL = (lastNamemax.length() < 1)?lastNamemax.length():1;
        lastNamemax = lastNamemax.substring(0, maxLengthL);  
        
        email = firstNamemax.toLowerCase() + lastNamemax.toLowerCase() + yearInString + "@uia.no";
           // Creating a new StudentMethods object and calling upon the addUser method.
           // Creating a dbconnection object
            StudentMethods sm = new StudentMethods();
            DbConnection db = new DbConnection();
            db.Connect();
            sm.addUser(FirstName, LastName, pass, email, out);
            
          //Forwarding to index.html using a include method
            RequestDispatcher rs = request.getRequestDispatcher("index.html");
            rs.include(request, response);
            out.println("<div class=alert>Bruker opprettet. Du kan logge inn med:" + email + "</div>");
           
            /* RequestDispatcher dispatcher
            = request.getServletContext().getRequestDispatcher("/index.html");
             dispatcher.forward(request, response);
             out.println("test"); */
        }        
    }
}
 
