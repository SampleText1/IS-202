/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listdemo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author evenal
 */
@WebServlet("/deletestudent")
public class DeleteStudentServlet implements StringConstants
{

    protected void doIt(HttpServletRequest req, HttpServletResponse resp) {

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        doIt(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doIt(req, resp);
    }
}
