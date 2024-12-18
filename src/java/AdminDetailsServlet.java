import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/AdminDetailsServlet")
public class AdminDetailsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the logged-in admin id from session
        String adminid = (String) request.getSession().getAttribute("adminid");

        if (adminid != null) {
            Connection con = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                // Connect to the database
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/collage", "root", "");

                // Query to fetch admin details
                String query = "SELECT * FROM admin_register WHERE Admin_id = ?";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, adminid);
                rs = pstmt.executeQuery();

                String name = "", id = "", email = "", address = "", phono = "", qua = "", dob = "";
                InputStream photoStream = null;

                if (rs.next()) {
                    name = rs.getString("Fullname");
                    id = rs.getString("Admin_id");
                    email = rs.getString("Email_id");
                    address = rs.getString("Address");
                    phono = rs.getString("Phone_No");
                    qua = rs.getString("qualification");
                    dob = rs.getString("dob");
                    photoStream = rs.getBinaryStream("photo"); // Retrieve the photo as a binary stream
                }

                // Set content type for HTML page
                response.setContentType("text/html");

                // Start the HTML response with header
                response.getWriter().println("<html><head>");
                response.getWriter().println("<title>Admin Login - Divya Sri College Of Arts & Science</title>");
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
                response.getWriter().println(".box { width: 300px; height: 300px; margin: 20px auto; text-align: center; padding: 10px; border: 2px solid #003366; border-radius: 10px; background-color: #fff; box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1); overflow: hidden; }");
                response.getWriter().println("table { width: 80%; margin: 20px auto; border: 1px solid #ccc; border-collapse: collapse; }");
                response.getWriter().println("th, td { padding: 10px; text-align: left; border: 1px solid #ccc; }");
                response.getWriter().println("</style>");
                response.getWriter().println("</head><body>");

                // Main Header
                response.getWriter().println("<header>");
                response.getWriter().println("<div class='logo-container'>");
                response.getWriter().println("<img src='images/logo.jpg' alt='College Logo' class='logo'>");
                response.getWriter().println("<span class='name'>Divya Sri College Of Arts & Science</span>");
                response.getWriter().println("</div>");
                response.getWriter().println("<nav><ul><li><a href='admin.jsp'>Back</a></li></ul></nav>");
                response.getWriter().println("</header>");

                // Centered box for image display
                response.getWriter().println("<div class='box'>");
                if (photoStream != null) {
                    // Set a specific width and height for the image to ensure it fits inside the box
                    response.getWriter().println("<img src='data:image/jpeg;base64," + encodeImage(photoStream) + "' alt='Admin Photo' style='width: 100%; height: 400px; object-fit: cover;'>");
                }
                response.getWriter().println("</div>");

                // Content Section (admin details in table format)
                response.getWriter().println("<table>");
                response.getWriter().println("<tr><th>Field</th><th>Details</th></tr>");
                response.getWriter().println("<tr><td>Name</td><td>" + name + "</td></tr>");
                response.getWriter().println("<tr><td>Admin ID</td><td>" + id + "</td></tr>");
                response.getWriter().println("<tr><td>Email</td><td>" + email + "</td></tr>");
                response.getWriter().println("<tr><td>Address</td><td>" + address + "</td></tr>");
                response.getWriter().println("<tr><td>Phone</td><td>" + phono + "</td></tr>");
                response.getWriter().println("<tr><td>Qualification</td><td>" + qua + "</td></tr>");
                response.getWriter().println("<tr><td>Date of Birth</td><td>" + dob + "</td></tr>");
                response.getWriter().println("</table>");

                // Footer Section
                response.getWriter().println("<footer><p>&copy; 2024 Divya Sri College Of Arts & Science. All Rights Reserved.</p></footer>");

                response.getWriter().println("</body></html>");

            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("<script type='text/javascript'>");
                response.getWriter().println("alert('Error: " + e.getMessage().replace("'", "\\'") + "');");
                response.getWriter().println("window.location = document.referrer;");
                response.getWriter().println("</script>");
            } finally {
                // Close resources
                try {
                    if (rs != null) rs.close();
                    if (pstmt != null) pstmt.close();
                    if (con != null) con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    // Helper method to encode the image into a Base64 string
    private String encodeImage(InputStream photoStream) throws IOException {
        byte[] bytes = new byte[photoStream.available()];
        photoStream.read(bytes);
        return java.util.Base64.getEncoder().encodeToString(bytes);
    }
}
