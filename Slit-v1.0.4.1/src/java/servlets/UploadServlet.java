package servlets;
 
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import classes.UploadMethods;
import java.io.PrintWriter;
import lib.DbConnection;
 
@WebServlet("/uploadServlet")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class UploadServlet extends HttpServlet {
     
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
         try (PrintWriter out = response.getWriter()) {
        // gets values of text fields
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
         
        InputStream inputStream = null; // input stream of the upload file
         
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("photo");
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }
        UploadMethods um = new UploadMethods(); 
            DbConnection db = new DbConnection();
            db.Connect();
            um.addMethod(first_name, last_name, filePart, out); 
         
        Connection conn = null; // connection to the database
        String message = null;  // message will be sent back to client
         
        
            // sets the message in request scope
            request.setAttribute("Message", message);
             
            // forwards to the message page
            getServletContext().getRequestDispatcher("/student.html").forward(request, response);
        }
    }
}
