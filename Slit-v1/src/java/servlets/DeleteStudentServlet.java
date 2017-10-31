/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import classes.Code;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author prebentjemsland
 */
@WebServlet(name = "deleteStudentServlet", urlPatterns = {"/deleteStudentServlet"})
public class DeleteStudentServlet extends HttpServlet {

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
            out.println("<title>Servlet deleteStudentServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            String arg1 = request.getParameter("arg1");
            String arg2 = request.getParameter("arg2");
            if (arg1.equals ("firstName")){
            arg1 = "Fornavn";}
            else if (arg1.equals ("lastName")){
            arg1 = "Etternavn";}
            else if (arg1.equals ("email")){
            arg1 = "Epost";}
            else if (arg1.equals ("id")){
            arg1 = "ID";}
            else{
                arg1 = "failed to find argument";
        }
            
            out.println("<h1>Student med " +arg2 + " som " +arg1 + " slettet</h1>");
            out.println("<center>\n" +
"             <a href=\"ansatt.html\" class=\"abutton\">Tilbake</a>\n" +
"            </center>\n" +
"            <br>");
           
            
            Code dbCode = new Code(); 
            dbCode.Connect(out);
            dbCode.deleteStudent(arg1, arg2, out);
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
