package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class VulnerableApp {

    // ❌ SQL Injection
    public static void unsafeSql(String userInput) throws Exception {
        Connection conn = DriverManager.getConnection(
                "jdbc:h2:mem:testdb", "sa", "");

        Statement stmt = conn.createStatement();
        String query = "SELECT * FROM users WHERE name = '" + userInput + "'";
        stmt.execute(query);
    }

    // ❌ Command Injection
    public static void unsafeCommand(String userInput) throws Exception {
        Runtime.getRuntime().exec(userInput);
    }

    // ❌ Path Traversal
    public static void unsafeFileRead(String fileName) throws Exception {
        BufferedReader reader =
                new BufferedReader(new FileReader("/tmp/" + fileName));
        reader.readLine();
        reader.close();
    }

    // ❌ Hardcoded credentials
    private static final String DB_PASSWORD = "HardCodedPassword123";

    public static void main(String[] args) throws Exception {
        if (args.length > 0) {
            unsafeSql(args[0]);
            unsafeCommand(args[0]);
            unsafeFileRead(args[0]);
        }
    }
}
