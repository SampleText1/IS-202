package servlets;
 
import classes.ModuleMethods;
import java.io.IOException;
import java.io.PrintWriter;
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
            out.println("<title>Modul lagt til</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Modul lagt til</h1>");
            out.println("<center>\n" +
"             <a href=\"ansatt.html\" class=\"abutton\">Tilbake</a>\n" +
"            </center>\n" +
"            <br>");
            out.println("<div class=list>"); 
           
         String title = request.getParameter("title");
         String id = request.getParameter("id");
         String description = request.getParameter("description");
         String goals = request.getParameter("goals");
         String task = request.getParameter("task");
           
            ModuleMethods mm = new ModuleMethods();
            mm.Connect(out);
            mm.addModule(id, title, description, goals, task, out);
           
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }      

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
       
     
  }
       
       
       
       
    }