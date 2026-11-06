package src;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddStudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        try {
            String name = req.getParameter("name");
            double cgpa = Double.parseDouble(req.getParameter("cgpa"));

            PlacementOfficer po = new PlacementOfficer();
            po.addStudent(name, cgpa);

            res.getWriter().println("Student Added Successfully");

        } catch (Exception e) {
            res.getWriter().println("Error: " + e.getMessage());
        }
    }
}
