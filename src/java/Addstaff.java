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

@WebServlet("/Addstaff")
@MultipartConfig(maxFileSize = 16177215) // 16MB limit for file upload
public class Addstaff extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database configuration
    private static final String DB_URL = "jdbc:mysql://localhost:3306/collage"; // Replace with your database name
    private static final String DB_USER = "root"; // Replace with your database username
    private static final String DB_PASSWORD = ""; // Replace with your database password

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetching form data
        String staffId = request.getParameter("staff_id");
        String staffName = request.getParameter("name");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String dep = request.getParameter("department");
        String qualification = request.getParameter("qualification");
        String experience = request.getParameter("experience");
        String salary = request.getParameter("salary");
        Part photoPart = request.getPart("photo"); // For photo upload
        Part documentPart = request.getPart("dc"); // For document upload

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL query (Excluding 'id' as it's auto-generated)
            String sql = "INSERT INTO staffs (staff_id, name, dob, gender, address, phone, email, department, qualification, experience, salary, photo, document) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            pstmt = conn.prepareStatement(sql);

            // Set parameters
            pstmt.setString(1, staffId);
            pstmt.setString(2, staffName);
            pstmt.setString(3, dob);
            pstmt.setString(4, gender);
            pstmt.setString(5, address);
            pstmt.setString(6, phone);
            pstmt.setString(7, email);
            pstmt.setString(8, dep);
            pstmt.setString(9, qualification);
            pstmt.setString(10, experience);
            pstmt.setString(11, salary);

            // Save photo as a binary stream
            if (photoPart != null) {
                pstmt.setBinaryStream(12, photoPart.getInputStream(), (int) photoPart.getSize());
            } else {
                pstmt.setNull(12, java.sql.Types.BLOB);
            }

            // Save document as a binary stream
          if (documentPart != null && documentPart.getSize() > 0) {
    pstmt.setBinaryStream(13, documentPart.getInputStream(), (int) documentPart.getSize());
} else {
    pstmt.setNull(13, java.sql.Types.BLOB); // Set NULL if no document is uploaded
}

            // Execute the query
            int row = pstmt.executeUpdate();

            response.setContentType("text/html");
            response.getWriter().println("<script type='text/javascript'>");
            if (row > 0) {
                response.getWriter().println("alert('Staff details added successfully!');");
            } else {
                response.getWriter().println("alert('Failed to add staff details.');");
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
