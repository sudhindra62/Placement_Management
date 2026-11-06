package src;

// backend logic
import java.sql.*;

public class PlacementOfficer extends User implements PlacementOperations {

    Connection conn;

    public PlacementOfficer() throws Exception {
        conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/placement_db",
            "root",
            "YOUR_PASSWORD"
        );
    }

    public void login() {
        System.out.println("Officer logged in");
    }

    public void addStudent(String name, double cgpa) throws Exception {
        if (cgpa < 0 || cgpa > 10)
            throw new Exception("Invalid CGPA");

        PreparedStatement ps = conn.prepareStatement(
            "INSERT INTO students(name,cgpa) VALUES (?,?)");
        ps.setString(1, name);
        ps.setDouble(2, cgpa);
        ps.executeUpdate();
    }

    public boolean checkEligibility(int sid, int cid) throws Exception {

        PreparedStatement s =
            conn.prepareStatement("SELECT cgpa FROM students WHERE id=?");
        s.setInt(1, sid);
        ResultSet rs1 = s.executeQuery();

        PreparedStatement c =
            conn.prepareStatement("SELECT min_cgpa FROM companies WHERE id=?");
        c.setInt(1, cid);
        ResultSet rs2 = c.executeQuery();

        if (!rs1.next() || !rs2.next())
            throw new Exception("Invalid ID");

        return rs1.getDouble(1) >= rs2.getDouble(1);
    }

    public void applyCompany(int sid, int cid) throws Exception {

        if (!checkEligibility(sid, cid))
            throw new Exception("Not eligible");

        PreparedStatement ps = conn.prepareStatement(
            "INSERT INTO applications(student_id,company_id) VALUES (?,?)");
        ps.setInt(1, sid);
        ps.setInt(2, cid);
        ps.executeUpdate();
    }
}
