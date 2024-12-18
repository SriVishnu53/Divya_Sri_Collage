<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Marks</title>
    <link href="https://fonts.googleapis.com/css2?family=Cinzel:wght@700&display=swap" rel="stylesheet">
    <style>
        /* Add your CSS styling here */
        body {
            font-family: 'Cinzel', serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #003366;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 8px;
            text-align: center;
        }

        th {
            background-color: #003366;
            color: #fff;
        }

        .footer {
            text-align: center;
            margin-top: 30px;
            font-size: 0.8em;
            color: #333;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Student Marks</h1>

        <table>
            <tr>
                <th>Name</th>
                <td>${studentName}</td>
            </tr>
            <tr>
                <th>Registration No.</th>
                <td>${regNo}</td>
            </tr>
            <tr>
                <th>Class</th>
                <td>${class}</td>
            </tr>
            <tr>
                <th>Department</th>
                <td>${department}</td>
            </tr>
            <tr>
                <th>Semester</th>
                <td>${semester}</td>
            </tr>
        </table>

        <h2>Subject-wise Marks</h2>
        <table>
            <tr>
                <th>Subject</th>
                <th>Internal Marks</th>
                <th>Semester Marks</th>
            </tr>
            <tr>
                <td>${subject1}</td>
                <td>${internalMarks1}</td>
                <td>${semesterMarks1}</td>
            </tr>
            <tr>
                <td>${subject2}</td>
                <td>${internalMarks2}</td>
                <td>${semesterMarks2}</td>
            </tr>
            <tr>
                <td>${subject3}</td>
                <td>${internalMarks3}</td>
                <td>${semesterMarks3}</td>
            </tr>
            <tr>
                <td>${subject4}</td>
                <td>${internalMarks4}</td>
                <td>${semesterMarks4}</td>
            </tr>
            <tr>
                <td>${subject5}</td>
                <td>${internalMarks5}</td>
                <td>${semesterMarks5}</td>
            </tr>
            <tr>
                <td>${subject6}</td>
                <td>${internalMarks6}</td>
                <td>${semesterMarks6}</td>
            </tr>
        </table>

        <h2>Total Marks</h2>
        <table>
            <tr>
                <th>Grand Total</th>
                <td>${grandTotal}</td>
            </tr>
            <tr>
                <th>Percentage</th>
                <td>${percentage}%</td>
            </tr>
        </table>
    </div>

    <div class="footer">
        <p>&copy; 2024 Divya Sri College Of Arts & Science. All Rights Reserved.</p>
    </div>
</body>
</html>
