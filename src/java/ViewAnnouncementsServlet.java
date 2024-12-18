import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DisplayData")
public class ViewAnnouncementsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Database connection details
        String jdbcURL = "jdbc:mysql://localhost:3306/collage";
        String dbUser = "root";
        String dbPassword = ""; // Ensure the password matches your MySQL setup

        // Ensure the MySQL JDBC Driver is loaded
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            response.getWriter().println("<p>Error: MySQL JDBC Driver not found. Please include the driver in your classpath.</p>");
            e.printStackTrace();
            return;
        }

        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT Title, Description, Dc FROM announcement")) {

            // HTML response with header
            response.getWriter().println("<html><head>");
            response.getWriter().println("<title>Annoucnments- Divya Sri College Of Arts & Science</title>");
            response.getWriter().println("<link href='https://fonts.googleapis.com/css2?family=Cinzel:wght@700&display=swap' rel='stylesheet'>");
            response.getWriter().println("<style>");
            response.getWriter().println("body { font-family: 'Cinzel', serif; margin: 0; padding: 0; background-image: url('images/bg.png'); background-size: cover; background-attachment: fixed; background-position: center; }");
            response.getWriter().println("header { background-color: #003366; color: #fff; padding: 1em; display: flex; justify-content: space-between; align-items: center; }");
            response.getWriter().println(".logo-container { display: flex; align-items: center; }");
            response.getWriter().println(".logo { width: 80px; height: 80px; border-radius: 60%; object-fit: cover; margin-right: 10px; }");
            response.getWriter().println(".name { font-size: 1.5em; font-weight: bold; color: #fff; }");
            response.getWriter().println("header nav ul { list-style: none; display: flex; gap: 40px; margin: 0; padding: 0; font-size: 1.2em; }");
            response.getWriter().println("header nav ul li { display: inline-block; }");
            response.getWriter().println("header nav ul li a { color: #003366; text-decoration: none; font-weight: bold; padding: 5px 10px; background-color: #fff; border-radius: 5px; font-size: 1.0em; }");
            response.getWriter().println("header nav ul li a:hover { background-color: #f2f2f2; text-decoration: underline; }");
            response.getWriter().println(".college-name { text-align: center; font-size: 1.8em; font-weight: bold; color: #003366; padding: 0.5em 1em; text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3); margin-top: 0; }");
           response.getWriter().println("footer {background-color: #003366; color: #fff; text-align: center; padding: 0.05em 0; margin-top: 0; font-size: 0.8em; line-height: 0.5; }");
            response.getWriter().println(".announcement { margin: 20px auto; padding: 20px; background-color: #ffffff; border-radius: 10px; max-width: 800px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); }");
            response.getWriter().println(".announcement h2 { text-align: center; color: #003366; text-decoration: underline; font-size: 1.5em; margin-bottom: 10px; }");
            response.getWriter().println(".announcement p { color: #555; font-size: 1.1em; line-height: 1.6; }");
            response.getWriter().println(".announcement img { margin-top: 10px; border-radius: 10px; max-width: 100%; height: auto; }");
            response.getWriter().println("</style></head>");
            response.getWriter().println("<body>");
            response.getWriter().println("<header>");
            response.getWriter().println("<div class='logo-container'>");
            response.getWriter().println("<img src='images/logo.jpg' alt='College Logo' class='logo'>");
            response.getWriter().println("<span class='name'>Divya Sri College Of Arts & Science</span>");
            response.getWriter().println("</div>");
            response.getWriter().println("<nav><ul><li><a href='index.html'>Back</a></li></ul></nav>");
            response.getWriter().println("</header>");
            response.getWriter().println("<h1 class='college-name'>Latest Announcements</h1>");

            // Display announcements
            while (rs.next()) {
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                byte[] imageBytes = rs.getBytes("Dc");

                response.getWriter().println("<div class='announcement'>");
                response.getWriter().println("<h2>" + title + "</h2>");
                response.getWriter().println("<p>" + description + "</p>");
                if (imageBytes != null) {
                    String base64Image = java.util.Base64.getEncoder().encodeToString(imageBytes);
                    response.getWriter().println("<img src='data:image/jpeg;base64," + base64Image + "' alt='Announcement Image'>");
                }
                response.getWriter().println("</div>");
            }

            // Footer
            response.getWriter().println("<footer><p>&copy; 2024 Divya Sri College Of Arts & Science. All Rights Reserved.</p></footer>");
            response.getWriter().println("</body></html>");

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("<p>Error retrieving data: " + e.getMessage() + "</p>");
        }
    }
}
