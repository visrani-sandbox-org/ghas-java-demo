import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class VulnerableTest {

    // Intentionally vulnerable method
    public static void vulnerableLogin(String userInput) {
        try {
            // Dummy DB connection (your tools will still pick up the pattern)
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "password");

            Statement stmt = conn.createStatement();

            // SOURCE → userInput (no validation)
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

            // SINK → SQL Injection
            System.out.println("[Executing] " + query);
            stmt.executeQuery(query); // Vulnerable call

            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Exception during DB operation: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // If no argument is passed, provide a default tainted input
        String username = args.length > 0 ? args[0] : "' OR 1=1 --";
        
        // Trigger the vulnerability
        vulnerableLogin(username);
    }
}
