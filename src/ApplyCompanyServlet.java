package src;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ApplyCompanyServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        try {
            int sid = Integer.parseInt(req.getParameter("sid"));
            int cid = Integer.parseInt(req.getParameter("cid"));

            PlacementOfficer po = new PlacementOfficer();
            po.applyCompany(sid, cid);

            res.getWriter().println("Application Successful");

        } catch (Exception e) {
            res.getWriter().println("Failed: " + e.getMessage());
        }
    }
}
