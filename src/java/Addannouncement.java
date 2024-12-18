/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;

/**
 *
 * @author VISHNU
 */
@WebServlet(urlPatterns = {"/Addannouncement"})
@MultipartConfig(maxFileSize = 16177215)
public class Addannouncement extends HttpServlet {
   
  // Database configuration
    private static final String DB_URL = "jdbc:mysql://localhost:3306/collage"; // Replace with your database name
    private static final String DB_USER = "root"; // Replace with your database username
    private static final String DB_PASSWORD = ""; // Replace with your database password

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetching form data
        String topic = request.getParameter("Title");
        String details = request.getParameter("Details");
       
        Part photoPart = request.getPart("dc"); // For file upload

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL query (Excluding 'id' as it's auto-generated)
           String sql = "INSERT INTO announcement (Title, Description, Dc) VALUES (?, ?, ?)";
pstmt = conn.prepareStatement(sql);

// Set parameters
pstmt.setString(1, topic);
pstmt.setString(2, details);

// Save photo as a binary stream
if (photoPart != null) {
    pstmt.setBinaryStream(3, photoPart.getInputStream(), (int) photoPart.getSize());
} else {
    pstmt.setNull(3, java.sql.Types.BLOB);
}

int row = pstmt.executeUpdate();


            // Execute the query
        

           response.setContentType("text/html");
            response.getWriter().println("<script type='text/javascript'>");
            if (row > 0) {
                response.getWriter().println("alert('Announcement details added successfully!');");
            } else {
                response.getWriter().println("alert('Failed to add Announcement details.');");
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