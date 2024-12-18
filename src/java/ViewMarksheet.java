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

@WebServlet("/ViewMarksheet")
public class ViewMarksheet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        String studentId = (String) session.getAttribute("student_id");
        
        if (studentId == null) {
            response.sendRedirect("login.jsp"); // Redirect if not logged in
            return;
        }
        
        try (PrintWriter out = response.getWriter()) {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/collage", "root", "");
            
            String query = "SELECT * FROM student_marks WHERE reg_no = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, studentId);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                // Forward data to the JSP
                request.setAttribute("marksData", rs);
                request.getRequestDispatcher("marksheet.jsp").forward(request, response);
            } else {
                out.println("<h3>No marks found for the logged-in student.</h3>");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
