import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

@WebServlet(urlPatterns = {"/Removestaff"})
public class Removestaff extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            // Get the staff ID from the request
            String id = request.getParameter("staff_id");

            // Database connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/collage", "root", "");

            // SQL query to delete staff
            String q = "DELETE FROM staffs WHERE staff_id = ?";
            PreparedStatement pst = con.prepareStatement(q);
            pst.setString(1, id);
            int row = pst.executeUpdate();  // Execute the deletion query

            // JavaScript response based on the success or failure of the operation
            response.setContentType("text/html");
            response.getWriter().println("<script type='text/javascript'>");

            if (row > 0) {
                response.getWriter().println("alert('Staff member removed successfully!');");
                response.getWriter().println("window.location = 'admin.jsp';"); // Redirect to admin log page on success
            } else {
                response.getWriter().println("alert('No staff member found with the given ID.');");
                response.getWriter().println("window.location = document.referrer;"); // Stay on the same page on failure
            }

            response.getWriter().println("</script>");
            con.close(); // Close the database connection

        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception
            response.setContentType("text/html");
            response.getWriter().println("<script type='text/javascript'>");
            response.getWriter().println("alert('Error: " + e.getMessage().replace("'", "\\'") + "');");
            response.getWriter().println("window.location = document.referrer;"); // Go back to the previous page on error
            response.getWriter().println("</script>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Removestaff.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Removestaff.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Removestaff.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Removestaff.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
