/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static sun.misc.MessageUtils.where;

/**
 *
 * @author VISHNU
 */
@WebServlet(urlPatterns = {"/Staff_login"})
public class Staff_login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Call the method to process the login
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String adminid = request.getParameter("username");
            String password = request.getParameter("password");

            // Database connection code
            try {
                // Load JDBC driver
                Class.forName("com.mysql.jdbc.Driver");

                // Establish connection to database
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/collage", "root", "");

                String q = "SELECT * FROM staffs";  // SQL query to fetch staff data
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(q);

                boolean validUser = false;
                while (rs.next()) {
                    String id = rs.getString("staff_id");
                    String pass = rs.getString("dob");
                    if (id.equalsIgnoreCase(adminid) && pass.equalsIgnoreCase(password)) {
                        validUser = true;

                        // Create a session and store the staff_id
                        HttpSession session = request.getSession();
                        session.setAttribute("staff_id", adminid);

                        break;
                    }
                }

                if (validUser) {
                    response.sendRedirect("staff.jsp");
                } else {
                    out.println("Username or password is incorrect.");
                }

                // Close resources after use
                rs.close();
                st.close();
                con.close();
            } catch (ClassNotFoundException e) {
                out.println("JDBC Driver not found: " + e.getMessage());
                e.printStackTrace();
            } catch (SQLException e) {
                out.println("Database error: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                out.println("General error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
