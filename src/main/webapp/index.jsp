<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>GHAS Java Demo</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 50px;
            background-color: #f5f5f5;
        }
        .container {
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            max-width: 600px;
            margin: 0 auto;
        }
        h1 {
            color: #333;
            border-bottom: 2px solid #007bff;
            padding-bottom: 10px;
        }
        p {
            color: #666;
            line-height: 1.6;
        }
        .info {
            background-color: #e7f3ff;
            padding: 15px;
            border-left: 4px solid #007bff;
            margin: 20px 0;
        }
        form {
            margin-top: 20px;
        }
        input[type="text"] {
            padding: 10px;
            width: 300px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        input[type="submit"] {
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-left: 10px;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to GHAS Java Demo</h1>
        
        <div class="info">
            <strong>About this application:</strong>
            <p>This is a simple Java web application designed to test GitHub Advanced Security features.</p>
        </div>
        
        <p>This application demonstrates:</p>
        <ul>
            <li>Java Servlet technology</li>
            <li>JavaServer Pages (JSP)</li>
            <li>Maven build with WAR packaging</li>
            <li>GitHub Advanced Security integration</li>
        </ul>
        
        <h2>Try the Hello Servlet</h2>
        <form action="hello" method="get">
            <label for="name">Enter your name:</label><br>
            <input type="text" id="name" name="name" placeholder="Your name">
            <input type="submit" value="Say Hello">
        </form>
        
        <p style="margin-top: 30px; color: #999; font-size: 0.9em;">
            Current server time: <%= new java.util.Date() %>
        </p>
    </div>
</body>
</html>
