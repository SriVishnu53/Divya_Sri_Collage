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

@WebServlet("/Admin_register")
@MultipartConfig(maxFileSize = 16177215) // 16MB limit for file upload
public class Admin_register extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database configuration
    private static final String DB_URL = "jdbc:mysql://localhost:3306/collage"; // Replace with your database name
    private static final String DB_USER = "root"; // Replace with your database username
    private static final String DB_PASSWORD = ""; // Replace with your database password

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetching form data
        String name = request.getParameter("name");
        String adminid = request.getParameter("id");
        
        String email = request.getParameter("email");
        String address= request.getParameter("address");
        String phone = request.getParameter("phone");
        String qualification = request.getParameter("q");
        String dob = request.getParameter("dob");
        String pass = request.getParameter("p");
        String cpass = request.getParameter("cp");
        
        Part photoPart = request.getPart("photo"); // For file upload

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL query (Excluding 'id' as it's auto-generated)
            String sql = "INSERT INTO admin_register (Fullname, Admin_id, Email_id, Address, Phone_No, qualification, dob, password, conformpassword, photo) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            pstmt = conn.prepareStatement(sql);

            // Set parameters
            pstmt.setString(1, name);
            pstmt.setString(2, adminid);
            pstmt.setString(3, email);
            pstmt.setString(4, address);
            pstmt.setString(5, phone);
            pstmt.setString(6, qualification);
            pstmt.setString(7, dob);
            pstmt.setString(8, pass);
            pstmt.setString(9, cpass);
           

            // Save photo as a binary stream
            if (photoPart != null) {
                pstmt.setBinaryStream(10, photoPart.getInputStream(), (int) photoPart.getSize());
            } else {
                pstmt.setNull(10, java.sql.Types.BLOB);
            }

            // Execute the query
            int row = pstmt.executeUpdate();

           response.setContentType("text/html");
           response.getWriter().println("<script type='text/javascript'>");
if (row > 0) {
    response.getWriter().println("alert('Register successfully!');");
    response.getWriter().println("window.location = 'adminlog.html';"); // Redirect to adminlog.html on success
} else {
    response.getWriter().println("alert('Failed to Register.');");
    response.getWriter().println("window.location = document.referrer;"); // Stay on the same page on failure
}
response.getWriter().println("</script>");

        }  catch (Exception e) {
    e.printStackTrace(); // Print stack trace to the server log
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