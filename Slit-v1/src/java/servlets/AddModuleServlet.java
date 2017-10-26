package servlets;
 
import classes.ModuleMethods;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 *
 * @author Mikael
 */
@WebServlet(name = "AddModuleServlet", urlPatterns = {"/AddModuleServlet"})
public class AddModuleServlet extends HttpServlet {
 
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
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">");
            out.println("<title>Student lagt til</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddModuleServlet at " + request.getContextPath() + "</h1>");
           
         String title = request.getParameter("title");
         String id = request.getParameter("id");
         String description = request.getParameter("description");
         String goals = request.getParameter("goals");
         //String resources = request.getParameter("resources");
         String task = request.getParameter("task");
         //String deadline = request.getParameter("deadline");
         
         //Date deadline2 = Date.valueOf(deadline);
           
            ModuleMethods mm = new ModuleMethods();
            mm.Connect(out);
            mm.addModule(id, title, description, goals, task, out);
           
           /* RequestDispatcher rd = request.getRequestDispatcher("hentStudenter");
            rd.forward(request, response); */
           
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