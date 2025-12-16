package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VulnerableController {

    // ❌ SQL Injection vulnerability
    public void unsafeSql(HttpServletRequest request) throws Exception {
        String userId = request.getParameter("id");

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/testdb", "root", "password");

        Statement stmt = conn.createStatement();
        String query = "SELECT * FROM users WHERE id = '" + userId + "'";
        stmt.executeQuery(query);
    }

    // ❌ Command Injection vulnerability
    public void unsafeCommand(HttpServletRequest request) throws IOException {
        String cmd = request.getParameter("cmd");
        Runtime.getRuntime().exec(cmd);
    }

    // ❌ Path Traversal vulnerability
    public void unsafeFileRead(HttpServletRequest request) throws IOException {
        String file = request.getParameter("file");
        java.nio.file.Files.readAllBytes(
                java.nio.file.Paths.get("/var/data/" + file));
    }

    // ❌ Hardcoded credentials
    private static final String DB_PASSWORD = "SuperSecretPassword123";

    // ❌ Reflected XSS vulnerability
    public void unsafeXss(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String input = request.getParameter("q");
        response.getWriter().write("<html><body>" + input + "</body></html>");
    }
}
