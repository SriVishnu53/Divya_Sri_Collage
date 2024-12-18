import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/Addstudent")
@MultipartConfig(maxFileSize = 16177215) // 16MB limit for file upload
public class Addstudent extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database configuration
    private static final String DB_URL = "jdbc:mysql://localhost:3306/collage"; // Replace with your database name
    private static final String DB_USER = "root"; // Replace with your database username
    private static final String DB_PASSWORD = ""; // Replace with your database password

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetching form data
        String studentId = request.getParameter("student_id");
        String studentName = request.getParameter("name");
        String fatherName = request.getParameter("father_name");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String course = request.getParameter("course");
        String qualification = request.getParameter("qualification");
        Part photoPart = request.getPart("photo"); // For file upload

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL query (Excluding 'id' as it's auto-generated)
            String sql = "INSERT INTO students (student_id, student_name, father_name, dob, gender, address, phone_no, email, course, qualification, photo) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            pstmt = conn.prepareStatement(sql);

            // Set parameters
            pstmt.setString(1, studentId);
            pstmt.setString(2, studentName);
            pstmt.setString(3, fatherName);
            pstmt.setString(4, dob);
            pstmt.setString(5, gender);
            pstmt.setString(6, address);
            pstmt.setString(7, phone);
            pstmt.setString(8, email);
            pstmt.setString(9, course);
            pstmt.setString(10, qualification);

            // Save photo as a binary stream
            if (photoPart != null) {
                pstmt.setBinaryStream(11, photoPart.getInputStream(), (int) photoPart.getSize());
            } else {
                pstmt.setNull(11, java.sql.Types.BLOB);
            }

            // Execute the query
            int row = pstmt.executeUpdate();

           response.setContentType("text/html");
            response.getWriter().println("<script type='text/javascript'>");
            if (row > 0) {
                response.getWriter().println("alert('Student details added successfully!');");
            } else {
                response.getWriter().println("alert('Failed to add student details.');");
            }
            response.getWriter().println("window.location = document.referrer;"); // Stay on the same page
            response.getWriter().println("</script>");
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html");
            response.getWriter().println("<script type='text/javascript'>");
            response.getWriter().println("alert('Error: " + e.getMessage().replace("'", "\\'") + "');");
            response.getWriter().println("window.location = document.referrer;");
            response.getWriter().println("</script>");
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}