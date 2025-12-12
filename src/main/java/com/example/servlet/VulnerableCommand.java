package com.example.servlet;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class VulnerableCommand {

    // Intentionally vulnerable method
    public static void runSystemCommand(String userInput) {
        try {
            // SOURCE → user-provided string (untrusted)
            String command = "ping -c 1 " + userInput;

            System.out.println("[Executing] " + command);

            // SINK → Runtime.exec() directly using unsanitized input
            Process process = Runtime.getRuntime().exec(command);

            // Read output (not required for detection but helpful)
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("[Output] " + line);
            }

            process.waitFor();

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Default tainted input if nothing is passed
        // Will trigger command injection patterns in security tools
        String host = args.length > 0 ? args[0] : "localhost; echo INJECTED";

        // Trigger the vulnerability
        runSystemCommand(host);
    }
}
