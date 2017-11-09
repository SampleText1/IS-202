package Loginsystem;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class Login extends HttpServlet {
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        
        if(Validate.checkAdmin(email, pass))
        {
            RequestDispatcher rs = request.getRequestDispatcher("ansatt.html");
            rs.forward(request, response);
        }
        else if(Validate.checkUser(email, pass))
        {
            RequestDispatcher rs = request.getRequestDispatcher("student.html");
            rs.forward(request, response);
        }
        else
        {
           out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
           out.println("<div class=alert>Feil email eller passord!</div>");
           RequestDispatcher rs = request.getRequestDispatcher("index.html");
           rs.include(request, response);
        }
    }  
}