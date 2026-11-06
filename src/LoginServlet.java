package src;

// backend controllers
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        try {
            String u = req.getParameter("user");
            String p = req.getParameter("pass");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/placement_db",
                "root","YOUR_PASSWORD");

            PreparedStatement ps =
                conn.prepareStatement(
                "SELECT * FROM users WHERE username=? AND password=?");

            ps.setString(1, u);
            ps.setString(2, p);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
                res.sendRedirect("dashboard.jsp");
            else
                res.getWriter().println("Invalid Login");

        } catch (Exception e) {
            res.getWriter().println("Login Error");
        }
    }
}
