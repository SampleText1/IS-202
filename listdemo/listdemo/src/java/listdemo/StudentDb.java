package listdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;


/**
 * This class fakes a database connection (actually it fakes an EJB
 * that manages a database table). We will get more into that when we start
 * working with EJBs and databases.
 *
 * @author evenal
 */
public class StudentDb implements StringConstants
{
    /** This is the "database" */
    Map<Long, Student> idMap;

    /**
     * Constructor - creates dummy test data
     */
    public StudentDb() {
        idMap = new HashMap<Long, Student>();

        addStudent(new Student("Per", "Grua", "Ikke snill"));
        addStudent(new Student("Paal", "Grua", "Enda verre"));
        addStudent(new Student("Espen", "Grua", "Helten"));
        addStudent(new Student("Kongen", "Slottet", "Slu"));
        addStudent(new Student("Prinsessa", "Slottet", "Kverulant"));
        addStudent(new Student("Heksa", "Skogen", "Kan trylle"));
    }

    public static StudentDb getInstance(HttpServlet servlet) {
        ServletContext context = servlet.getServletContext();
        StudentDb db = (StudentDb) context.getAttribute(STUDENT_DB);
        if (db == null) {
            db = new StudentDb();
            context.setAttribute(STUDENT_DB, db);
        }
        return db;
    }

    // methods for adding, deleting etc. students
    public boolean addStudent(Student s) {
        if (!idMap.containsKey(s.getId())) {
            idMap.put(s.getId(), s);
            return true;
        }
        else return false;
    }

    public boolean updateStudent(Student s) {
        idMap.put(s.getId(), s);
        return true;
    }

    public Student getStudent(long id) {
        return idMap.get(id);
    }

    public boolean removeStudent(long id) {
        Student s = getStudent(id);
        idMap.remove(s.getId());
        return true;
    }

    public List<Student> getStudents() {
        return new ArrayList<Student>(idMap.values());
    }
}
