package servlets;
 
import classes.StudentMethods;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lib.DbConnection;
 
/**
 *
 * @author Mikael
 */
@WebServlet(name = "ListStudentsServlet", urlPatterns = {"/ListStudentsServlet"})
public class ListStudentsServlet extends HttpServlet {
 
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
            out.println("<title>Studenter</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Studenter i databasen</h1>");
            out.println("<div class=list>");
           
           
            StudentMethods sm = new StudentMethods();
            DbConnection db = new DbConnection();
            db.Connect();
            sm.printStudents(out);
           
            out.println("</div>");
            out.println("<form action=\"deleteStudentServlet\" method=\"post\">\n" +
"             <p>\n" +
"             </p>\n" +
"             <input type=\"text\" name=\"arg2\" placeholder=\"ID\"><br>\n" +
"              <input type=\"submit\" name=\"hent\" value=\"Slett\">\n" +
"             </form>" +
            "<br>");
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
 
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
 
}