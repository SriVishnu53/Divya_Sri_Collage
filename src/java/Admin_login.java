import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/Admin_login"})
public class Admin_login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String adminid = request.getParameter("id");
            String password = request.getParameter("pass");

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/collage", "root", "");
            String q = "SELECT * FROM admin_register";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(q);

            boolean validLogin = false; // Flag to check if the login is valid
            while (rs.next()) {
                String id = rs.getString("Admin_id");
                String pass = rs.getString("password");

                if (id.equalsIgnoreCase(adminid) && pass.equalsIgnoreCase(password)) {
                    // Login is successful
                    validLogin = true;

                    // Set the adminid in the session
                    HttpSession session = request.getSession();
                    session.setAttribute("adminid", adminid);

                    // Redirect to the admin page
                    response.sendRedirect("admin.jsp");
                    break;  // Exit loop after successful login
                }
            }

            if (!validLogin) {
                // If login failed
                out.println("Username or password is incorrect.");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Admin_login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Admin_login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Admin_login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Admin_login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
