package servlets;
 
import classes.StudentMethods;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lib.DbConnection;
import static org.apache.openejb.client.Client.request;
 
/**
 *
 * @author Mikael
 */
@WebServlet(name = "RegisterUserServlet", urlPatterns = {"/RegisterUserServlet"})
public class RegisterUserServlet extends HttpServlet {
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
         throws ServletException, IOException {
         try (PrintWriter out = response.getWriter()) {
        // gets values of text fields
       
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
            out.println("<title>Modul lagt til</title>");            
            out.println("</head>");
            out.println("<body>");
            
           
         String FirstName = request.getParameter("firstName");
         String LastName = request.getParameter("lastName");
         String pass = request.getParameter("pass");
         String email = request.getParameter ("email");
           
            StudentMethods sm = new StudentMethods();
            DbConnection db = new DbConnection();
            db.Connect();
            sm.Connect(out);
            sm.addUser(FirstName, LastName, pass, email, out);
           
           /* RequestDispatcher rd = request.getRequestDispatcher("hentStudenter");
            rd.forward(request, response); */
           
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    
         
        Connection conn = null; // connection to the database
        String message = null;  // message will be sent back to client

        {
           out.println("<div class=alert>Fil lastet opp</div>");
           RequestDispatcher rs = request.getRequestDispatcher("upload.html");
           rs.include(request, response);
        }
}
}
 