<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Marksheet</title>
    <style>
      /* Basic styling for the body */
body {
    font-family: 'Cinzel', serif;
    margin: 0;
    padding: 0;
    background-image: url('images/bg.png');
    background-size: cover;
    background-attachment: fixed;
    background-position: center;
}

/* Header styling */
header {
    background-color: #003366;
    color: #fff;
    padding: 1em;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.logo-container {
    display: flex;
    align-items: center;
}

.logo {
    width: 80px;
    height: 80px;
    border-radius: 50%;
    object-fit: cover;
    margin-right: 10px;
}

.name {
    font-size: 1.5em;
    font-weight: bold;
    color: #fff;
}

header nav ul {
    list-style: none;
    display: flex;
    gap: 40px;
    margin: 0;
    padding: 0;
    font-size: 1.2em;
}

header nav ul li a {
    color: #003366;
    text-decoration: none;
    font-weight: bold;
    padding: 5px 10px;
    background-color: #fff;
    border-radius: 5px;
    font-size: 1em;
}

header nav ul li a:hover {
    background-color: #f2f2f2;
    text-decoration: underline;
}

/* Enhanced marksheet styling */
.container {
    max-width: 900px;
    margin: 30px auto;
    background: #fff;
    padding: 40px;
    border-radius: 15px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

.report-title {
    text-align: center;
    font-size: 2.5em;
    margin-bottom: 25px;
    color: #003366;
    font-weight: bold;
    position: relative;
}

.report-title:after {
    content: "";
    display: block;
    width: 60px;
    height: 4px;
    background: #0056b3;
    margin: 10px auto;
    border-radius: 2px;
}

.student-info table {
    width: 100%;
    border-collapse: collapse;
}

.student-info td {
    padding: 12px;
    vertical-align: top;
    font-size: 1.1em;
}

.student-info th {
    font-size: 1.2em;
    text-align: left;
    background-color: #f1f8e9;
}

.student-info tr:nth-child(odd) {
    background-color: #f9f9f9;
}

/* Marks table with improved design */
.marks-table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
}

.marks-table th, .marks-table td {
    border: 1px solid #ccc;
    padding: 12px;
    text-align: center;
    font-size: 1.1em;
}

.marks-table th {
    background-color: #0056b3;
    color: #fff;
    text-transform: uppercase;
}

.marks-table tbody tr:nth-child(even) {
    background-color: #f8f9fa;
}

.marks-table tbody tr:hover {
    background-color: #d1e7ff;
    cursor: pointer;
}

/* Total marks section */
.total-container {
    text-align: center;
    padding: 30px;
    background: linear-gradient(to right, #e0f7fa, #f1f8e9);
    border-radius: 10px;
    font-size: 1.4em;
    margin-top: 20px;
}

/* Enhanced badge styling */
.badge {
    display: inline-block;
    margin-top: 10px;
    padding: 15px 30px;
    font-size: 1.2em;
    font-weight: bold;
    color: #fff;
    border-radius: 8px;
    transition: transform 0.3s ease-in-out;
}

.badge.distinction {
    background: #28a745;
}
.badge.first-class {
    background: #17a2b8;
}
.badge.second-class {
    background: #ffc107;
}
.badge.needs-improvement {
    background: #dc3545;
}

.badge:hover {
    transform: scale(1.1);
}

/* Section container improvements */
.marks-table-container {
    background: rgba(255, 255, 255, 0.85);
    border-radius: 8px;
    padding: 15px;
    box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
    margin-top: 20px;
}

footer {
    background-color: #003366;
    color: #fff;
    text-align: center;
    padding: 0.5em 0;
    font-size: 0.8em;
}

    </style>
</head>
<body>
    <header>
        <div class="logo-container">
            <img src="images/logo.jpg" alt="College Logo" class="logo">
            <h1>Divya Sri College Of Arts & Science</h1>
        </div>
        <nav>
            <ul>
                <li><a href="student.jsp">Back</a></li>
            </ul>
        </nav>
    </header>

    <div class="container">
        <div class="report-title">Student Marksheet</div>

        <% 
            String studentId = (String) session.getAttribute("student_id");
            if (studentId == null) {
        %>
            <p style='text-align: center; color: red;'>Please log in to view the marksheet</p>
        <% 
            } else {
                Connection con = null;
                PreparedStatement ps = null;
                ResultSet rs = null;

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost/collage", "root", "");

                    String query = "SELECT * FROM student_marks WHERE reg_no = ?";
                    ps = con.prepareStatement(query);
                    ps.setString(1, studentId);
                    rs = ps.executeQuery();

                    if (rs.next()) {
                        double totalMarks = 0, maxMarks = 0;
        %>
        <div class="student-info">
            <table>
                <tr>
                    <td><strong>Name:</strong></td>
                    <td><%= rs.getString("name") %></td>
                </tr>
                <tr>
                    <td><strong>Registration No:</strong></td>
                    <td><%= rs.getString("reg_no") %></td>
                </tr>
                <tr>
                    <td><strong>Class:</strong></td>
                    <td><%= rs.getString("class") %></td>
                </tr>
                <tr>
                    <td><strong>Department:</strong></td>
                    <td><%= rs.getString("department") %></td>
                </tr>
                <tr>
                    <td><strong>Semester:</strong></td>
                    <td><%= rs.getInt("semester") %></td>
                </tr>
            </table>
        </div>

        <div class="marks-table-container">
            <table class="marks-table">
                <thead>
                    <tr>
                        <th>Subject</th>
                        <th>Internal Marks</th>
                        <th>Semester Marks</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        for (int i = 1; i <= 6; i++) {
                            String subject = rs.getString("subject" + i);
                            double internal = rs.getDouble("internal_marks" + i);
                            double semester = rs.getDouble("semester_marks" + i);
                            totalMarks += internal + semester;
                            maxMarks += 100;
                    %>
                    <tr>
                        <td><%= subject %></td>
                        <td><%= internal %></td>
                        <td><%= semester %></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>

        <div class="total-container">
            <p>Total Marks: <%= totalMarks %> / <%= maxMarks %></p>
            <p>Percentage: <%= String.format("%.2f", (totalMarks / maxMarks) * 100) %>%</p>
            <% if ((totalMarks / maxMarks) * 100 >= 75) { %>
                <span class="badge distinction">Distinction</span>
            <% } else if ((totalMarks / maxMarks) * 100 >= 60) { %>
                <span class="badge first-class">First Class</span>
            <% } else if ((totalMarks / maxMarks) * 100 >= 50) { %>
                <span class="badge second-class">Second Class</span>
            <% } else { %>
                <span class="badge needs-improvement">Needs Improvement</span>
            <% } %>
        </div>
        <% 
                    } else {
        %>
            <p style='text-align: center; color: red;'>No marksheet found for the student</p>
        <% 
                    }
                } catch (Exception e) {
        %>
            <p style='text-align: center; color: red;'>Error: <%= e.getMessage() %></p>
        <% 
                } finally {
                    if (rs != null) rs.close();
                    if (ps != null) ps.close();
                    if (con != null) con.close();
                }
            }
        %>
    </div>

    <footer>
        &copy; 2024 Divya Sri College Of Arts & Science. All Rights Reserved.
    </footer>
</body>
</html>
