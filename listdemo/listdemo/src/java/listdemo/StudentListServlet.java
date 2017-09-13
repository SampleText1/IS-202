/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listdemo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author evenal
 */
@WebServlet("/studentlist")
public class StudentListServlet extends HttpServlet
{
    public static final String PREAMBLE
            = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "  <head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>ListDemo</title>\n" +
            "  </head>\n" +
            " <body>\n";
    public static final String STUDENT
            = "      <li><a href='studentdetail?id=%d'>%s</a></li>\n";

    protected void doIt(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        StudentDb db = getDb();
        List<Student> students = db.getStudents();

        PrintWriter out = resp.getWriter();
        out.format(PREAMBLE);
        out.format("    <h1>StudentList</h1>\n    <ul>\n");
        for (Student s : students) {
            out.format(STUDENT, s.getId(), s.getName());
        }
        out.format("    </ul>\n  </body>\n</html>");

    }

    protected StudentDb getDb() {
        ServletContext context = getServletContext();
        StudentDb db = (StudentDb) context.getAttribute("studentdb");
        if (db == null) {
            db = new StudentDb();
            context.setAttribute("studentdb", db);
        }
        return db;
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
