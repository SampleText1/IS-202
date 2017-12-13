    package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import classes.StudentMethods;
import lib.DbConnection;

/**
 *
 * @author Mikael
 */
@WebServlet(name = "addStudentServlet", urlPatterns = {"/addStudentServlet"})
public class AddStudentServlet extends HttpServlet {
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
            out.println("<title>Student lagt til</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<center>");
            out.println("<h3>Student lagt til</h3>");
            out.println("</center>");
            out.println("<div class=list>");
            
         String firstName = request.getParameter("firstName");
         String id = request.getParameter("id");
         String lastName = request.getParameter("lastName");
         String email = request.getParameter("email");
         String pass = request.getParameter("pass");
            
            StudentMethods sm = new StudentMethods();
            DbConnection db = new DbConnection();
            db.Connect();
            sm.addStudent(id, firstName, lastName, email, pass, out); 
            
           /* RequestDispatcher rd = request.getRequestDispatcher("hentStudenter"); 
            rd.forward(request, response); */
           
            out.println("</div>");
            out.println("<br>");
            out.println("<center>\n" +
"            <input type=\"button\" class=\"abutton\" onclick=\"history.back();\" value=\"Tilbake\">\n" +
"            </center>\n" +
"            <br>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
     
  }
        
        
        
        
    }

   


