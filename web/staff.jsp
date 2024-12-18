<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Staff</title>
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

        .sub-header {
            background-image: url('images/sub-header-bg.jpg');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            padding: 1em;
            display: flex;
            justify-content: space-around;
            align-items: center;
        }

        .sub-header .box {
            background-color: rgba(0, 51, 102, 0.8);
            color: #fff;
            padding: 10px 15px;
            border-radius: 5px;
            text-align: center;
        }

        .sub-header .box a {
            color: #fff;
            text-decoration: none;
            font-weight: bold;
            font-size: 1.2em;
        }

        .sub-header .box a:hover {
            text-decoration: underline;
        }

        .college-name {
            text-align: center;
            font-size: 1.8em;
            font-weight: bold;
            color: #003366;
            padding: 0.5em 1em;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
            margin-top: 0;
        }

        footer {
            background-color: #003366;
            color: #fff;
            text-align: center;
            padding: 0.05em 0;
            margin-top: auto;
            font-size: 0.8em;
            line-height: 0.5;
        }

        .welcome-message {
            text-align: center;
            font-size: 2em;
            font-weight: bold;
            color: #003366;
            margin-top: 20px;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
        }
        .admin-id {
            text-align: center;
            font-size: 1.5em;
            color: #003366;
            margin-top: 10px;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
        }
        
    </style>
</head>
<body>

    <header>
        <div class="logo-container">
            <img src="images/logo.jpg" alt="College Logo" class="logo">
            <span class="name">Divya Sri College Of Arts & Science</span>
        </div>
        <nav>
            <ul>
                <li><a href="index.html">Back</a></li>
            </ul>
        </nav>
    </header>

    <div class="sub-header">
        <div class="box">
            <a href="staffdetails">Staff Details</a>
        </div>
       <div class="box">
    <a href="ViewSalaryServlet">View_Salary</a>
</div>

        <div class="box">
            <a href="stumarkinsert.html">Add_Marks</a>
        </div>
       
    </div>
    <div class="welcome-message">
        Welcome to the Staff Page
    </div>
<div class="admin-id">
        Logged in as: <%= session.getAttribute("staff_id") %>
    </div>
    <footer>
        <p>&copy; 2024 Divya Sri College Of Arts & Science. All Rights Reserved.</p>
    </footer>

</body>
</html>



