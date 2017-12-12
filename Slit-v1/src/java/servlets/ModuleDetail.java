/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@WebServlet(name = "ModuleDetail", urlPatterns = {"/ModuleDetail"})
public class ModuleDetail extends HttpServlet {

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
            out.println("<title>Moduldetaljer</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Informasjon om modul</h1>");
            
            String id  = request.getParameter("id");
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            
            ModuleMethods dbCode = new ModuleMethods();
            dbCode.Connect(out);
            dbCode.skrivEnModul(id, out);
            //dbCode.close();
            
            
            out.println("</body>");
            out.println("</html>");
        }
    }
}
