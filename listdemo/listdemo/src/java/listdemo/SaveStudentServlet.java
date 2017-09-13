/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listdemo;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author evenal
 */
@WebServlet("/savestudent")
public class SaveStudentServlet extends HttpServlet
        implements StringConstants
{

    protected void doIt(HttpServletRequest req, HttpServletResponse resp) {
        String idStr = req.getParameter(ID);
        String name = req.getParameter(NAME);
        String address = req.getParameter(ADDRESS);
        String extra = req.getParameter(EXTRA);

        long id = Long.valueOf(idStr);
        StudentDb db = StudentDb.getInstance(this);

        if (id < 0) {
            Student s = new Student(name, address, extra);
            db.addStudent(s);
        }
        else {
            Student s = db.getStudent(id);
            s.setName(name);
            s.setAddress(address);
            s.setExtra(extra);
            db.updateStudent(s);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("studentlist");
        try {
            dispatcher.forward(req, resp);
        }
        catch (ServletException | IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        doIt(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doIt(req, resp);
    }
}
