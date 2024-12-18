<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Salary Slip</title>
    <link href="https://fonts.googleapis.com/css2?family=Cinzel:wght@700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Cinzel', serif;
            margin: 0;
            padding: 0;
            background-image: url('images/staffs.png');
            background-size: cover;
            background-attachment: fixed;
            background-position: center;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

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
            border-radius: 60%;
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
            font-size: 1.0em;
            display: inline-block;
        }

        header nav ul li a:hover {
            background-color: #f2f2f2;
            text-decoration: underline;
        }

        footer {
            background-color: #003366;
            color: #fff;
            text-align: center;
            padding: 0.5em 0;
            margin-top: auto;
            font-size: 0.8em;
            line-height: 0.5;
        }

        table {
            width: 50%;
            margin: 30px auto;
            border-collapse: collapse;
            background-color: #fff; /* Add white background for the table */
            border: 1px solid #003366;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #003366;
        }

        th {
            background-color: #003366;
            color: #fff;
            text-align: center; /* Center align the text for all header cells */
        }

        .salary-header {
            font-size: 1.5em;
            background-color: #003366;
            color: #fff;
            text-align: center;
        }
    </style>
</head>
<body>
    <!-- Header -->
    <header>
        <div class="logo-container">
            <img src="images/logo.jpg" alt="College Logo" class="logo">
            <span class="name">Divya Sri College Of Arts & Science</span>
        </div>
        <nav>
            <ul>
                <li><a href="staff.jsp">Back</a></li>
            </ul>
        </nav>
    </header>

    <!-- Main Content -->
    <table>
        <tr>
            <th colspan="2" class="salary-header">Salary Slip</th>
        </tr>
        <tr>
            <th>Description</th>
            <th>Amount</th>
        </tr>
        <tr>
            <td>Staff ID</td>
            <td>${staffId}</td>
        </tr>
        <tr>
            <td>Basic Salary</td>
            <td>${basicSalary}</td>
        </tr>
        <tr>
            <td>HRA (20%)</td>
            <td>${hra}</td>
        </tr>
        <tr>
            <td>DA (10%)</td>
            <td>${da}</td>
        </tr>
        <tr>
            <td>TA (5%)</td>
            <td>${ta}</td>
        </tr>
        <tr>
            <td>Gross Salary</td>
            <td>${grossSalary}</td>
        </tr>
        <tr>
            <td>PF (12%)</td>
            <td>${pf}</td>
        </tr>
        <tr>
            <td>Net Salary</td>
            <td>${netSalary}</td>
        </tr>
    </table>

    <!-- Footer -->
    <footer>
        <p>&copy; 2024 Divya Sri College Of Arts & Science. All Rights Reserved.</p>
    </footer>
</body>
</html>
