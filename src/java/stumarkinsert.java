import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "stumarkinsert", urlPatterns = {"/stumarkinsert"})
public class stumarkinsert extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            // Get data from the form
            String name = request.getParameter("name");
            String regNo = request.getParameter("reg");
            String studentClass = request.getParameter("class");
            String department = request.getParameter("dept");
            int semester = Integer.parseInt(request.getParameter("semester"));

            String subject1 = request.getParameter("su1");
            double internalMarks1 = Double.parseDouble(request.getParameter("i1"));
            double semesterMarks1 = Double.parseDouble(request.getParameter("e1"));

            String subject2 = request.getParameter("su2");
            double internalMarks2 = Double.parseDouble(request.getParameter("i2"));
            double semesterMarks2 = Double.parseDouble(request.getParameter("e2"));

            String subject3 = request.getParameter("su3");
            double internalMarks3 = Double.parseDouble(request.getParameter("i3"));
            double semesterMarks3 = Double.parseDouble(request.getParameter("e3"));

            String subject4 = request.getParameter("su4");
            double internalMarks4 = Double.parseDouble(request.getParameter("i4"));
            double semesterMarks4 = Double.parseDouble(request.getParameter("e4"));

            String subject5 = request.getParameter("su5");
            double internalMarks5 = Double.parseDouble(request.getParameter("i5"));
            double semesterMarks5 = Double.parseDouble(request.getParameter("e5"));

            String subject6 = request.getParameter("su6");
            double internalMarks6 = Double.parseDouble(request.getParameter("i6"));
            double semesterMarks6 = Double.parseDouble(request.getParameter("e6"));

            // Calculate totals
            double total1 = internalMarks1 + semesterMarks1;
            double total2 = internalMarks2 + semesterMarks2;
            double total3 = internalMarks3 + semesterMarks3;
            double total4 = internalMarks4 + semesterMarks4;
            double total5 = internalMarks5 + semesterMarks5;
            double total6 = internalMarks6 + semesterMarks6;

            double grandTotal = total1 + total2 + total3 + total4 + total5 + total6;
            double percentage = grandTotal / 6;

            // Database connection and insertion
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/collage", "root", "");

            String query = "INSERT INTO student_marks (name, reg_no, class, department, semester, "
                    + "subject1, internal_marks1, semester_marks1, "
                    + "subject2, internal_marks2, semester_marks2, "
                    + "subject3, internal_marks3, semester_marks3, "
                    + "subject4, internal_marks4, semester_marks4, "
                    + "subject5, internal_marks5, semester_marks5, "
                    + "subject6, internal_marks6, semester_marks6, "
                    + "total1, total2, total3, total4, total5, total6, grand_total, percentage) "
                    + "VALUES ('" + name + "', '" + regNo + "', '" + studentClass + "', '" + department + "', "
                    + semester + ", '" + subject1 + "', " + internalMarks1 + ", " + semesterMarks1 + ", "
                    + "'" + subject2 + "', " + internalMarks2 + ", " + semesterMarks2 + ", "
                    + "'" + subject3 + "', " + internalMarks3 + ", " + semesterMarks3 + ", "
                    + "'" + subject4 + "', " + internalMarks4 + ", " + semesterMarks4 + ", "
                    + "'" + subject5 + "', " + internalMarks5 + ", " + semesterMarks5 + ", "
                    + "'" + subject6 + "', " + internalMarks6 + ", " + semesterMarks6 + ", "
                    + total1 + ", " + total2 + ", " + total3 + ", " + total4 + ", " + total5 + ", " + total6 + ", "
                    + grandTotal + ", " + percentage + ")";

            Statement stmt = con.createStatement();
            int row = stmt.executeUpdate(query); // Get the number of affected rows

            // Display the alert based on the result
            response.getWriter().println("<script type='text/javascript'>");

            if (row > 0) {
                // If the operation was successful
                response.getWriter().println("alert('Student Mark Uploaded successfully!');");
                response.getWriter().println("window.location = 'stumarkinsert.html';"); // Redirect to another page (e.g., student.html)
            } else {
                // If the operation failed
                response.getWriter().println("alert('Failed to add student details.');");
                response.getWriter().println("window.location = document.referrer;"); // Stay on the same page
            }

            response.getWriter().println("</script>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
