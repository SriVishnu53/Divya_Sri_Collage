import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ViewSalaryServlet")
public class ViewSalaryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String staffId = (String) session.getAttribute("staff_id");

        if (staffId == null) {
            response.sendRedirect("staff.jsp"); // Redirect to login if not logged in
            return;
        }

        // Database connection setup
        String url = "jdbc:mysql://localhost:3306/collage";
        String user = "root";
        String password = ""; // Leave blank if no password

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT salary FROM staffs WHERE staff_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, staffId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double basicSalary = rs.getDouble("salary");

                // Calculate additional components
                double hra = 0.2 * basicSalary;
                double da = 0.1 * basicSalary;
                double ta = 0.05 * basicSalary;
                double pf = 0.12 * basicSalary;
                double grossSalary = basicSalary + hra + da + ta;
                double netSalary = grossSalary - pf;

                // Pass data to the JSP
                request.setAttribute("staffId", staffId);
                request.setAttribute("basicSalary", basicSalary);
                request.setAttribute("hra", hra);
                request.setAttribute("da", da);
                request.setAttribute("ta", ta);
                request.setAttribute("grossSalary", grossSalary);
                request.setAttribute("pf", pf);
                request.setAttribute("netSalary", netSalary);

                request.getRequestDispatcher("salarySlip.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "No salary details found for your ID.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
