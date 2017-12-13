/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import classes.Evaluation;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "GodkjenningServlet", urlPatterns = {"/GodkjenningServlet"})
public class GodkjenningServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          
            DbConnection db = new DbConnection();
            Evaluation ev = new Evaluation();    
            
            String id = request.getParameter("id");
            
            db.Connect();
            ev.godkjennModul(id, out);
            db.commit();
            
            RequestDispatcher dispatcher
            = request.getServletContext().getRequestDispatcher("/EvaluationServlet");
 
        dispatcher.forward(request, response);
        
        
        }
    }

}
