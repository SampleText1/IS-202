/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listdemo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * This servlet writes a page with a form, for viewing
 * and/or editing student data
 *
 * @author evenal
 */
@WebServlet("/studentdetail")
public class StudentDetailServlet extends HttpServlet
        implements StringConstants
{

    protected void doIt(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Student student = null;

        String id = req.getParameter("id");
        if (id != null) {
            StudentDb db = StudentDb.getInstance(this);
            long studno = Long.parseLong(id);
            student = db.getStudent(studno);
        }

        PrintWriter out = resp.getWriter();

        out.format(StudentListServlet.PREAMBLE);
        out.format("<h1>Student details</h1>\n");
        if (student == null) writeEmptyForm(out);
        else writeForm(out, student);
    }

    private void writeEmptyForm(PrintWriter out) {
        out.format("<form>\n<table>\n");
        out.format(ID_FIELD, "Student no.", ID, 0);
        out.format(TEXT_FIELD, "Name", NAME, "");
        out.format(TEXT_FIELD, "Address", ADDRESS, "");
        out.format(TEXT_FIELD, "Extra info", EXTRA, "");

        out.format(START_BUTTON_ROW);
        out.format(BUTTON, "Save");
        out.format(END_BUTTON_ROW);
        out.format("</table>\n</form>\n");
    }

    private void writeForm(PrintWriter out, Student student) {
        out.format("<form action='savestudent'>\n<table>\n");
        out.format(ID_FIELD, "Student no.", ID, student.getId());
        out.format(TEXT_FIELD, "Name", NAME, student.getName());
        out.format(TEXT_FIELD, "Address", ADDRESS, student.getAddress());
        out.format(TEXT_FIELD, "Extra info", EXTRA, student.getExtra());
        out.format(START_BUTTON_ROW);
        out.format(BUTTON, "Save");
        out.format(BUTTON, "deletestudent", DELETE);
        out.format(BUTTON_FORMACTION, "studentlist", CANCEL);
        out.format(END_BUTTON_ROW);
        out.format("</table>\n</form>\n</body>\n</html>");
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
