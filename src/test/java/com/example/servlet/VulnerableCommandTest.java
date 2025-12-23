package com.example.servlet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Test suite for VulnerableCommand
 * Note: This class tests intentionally vulnerable code for demonstration purposes
 */
public class VulnerableCommandTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testRunSystemCommandWithSimpleHost() {
        // Test with a simple hostname (localhost)
        VulnerableCommand.runSystemCommand("localhost");
        
        String output = outContent.toString();
        assertTrue(output.contains("[Executing]"));
        assertTrue(output.contains("ping -c 1 localhost"));
    }

    @Test
    public void testRunSystemCommandWithIPAddress() {
        // Test with an IP address
        VulnerableCommand.runSystemCommand("127.0.0.1");
        
        String output = outContent.toString();
        assertTrue(output.contains("[Executing]"));
        assertTrue(output.contains("ping -c 1 127.0.0.1"));
    }

    @Test
    public void testRunSystemCommandWithInjectionAttempt() {
        // Test with command injection attempt (intentionally vulnerable)
        VulnerableCommand.runSystemCommand("localhost; echo INJECTED");
        
        String output = outContent.toString();
        assertTrue(output.contains("[Executing]"));
        assertTrue(output.contains("ping -c 1 localhost; echo INJECTED"));
    }

    @Test
    public void testRunSystemCommandWithEmptyString() {
        // Test with empty string
        VulnerableCommand.runSystemCommand("");
        
        String output = outContent.toString();
        assertTrue(output.contains("[Executing]"));
        assertTrue(output.contains("ping -c 1 "));
    }

    @Test
    public void testRunSystemCommandWithSpecialCharacters() {
        // Test with special characters
        VulnerableCommand.runSystemCommand("test&test");
        
        String output = outContent.toString();
        assertTrue(output.contains("[Executing]"));
        assertTrue(output.contains("ping -c 1 test&test"));
    }

    @Test
    public void testRunSystemCommandCatchesExceptions() {
        // Test that exceptions are caught and handled
        // Using invalid command syntax to trigger exception
        VulnerableCommand.runSystemCommand("invalid|command|syntax|||");
        
        // The method should not throw an exception, it catches them internally
        // Check that either execution or exception message is printed
        String output = outContent.toString();
        assertTrue(output.contains("[Executing]") || output.contains("Exception"));
    }

    @Test
    public void testMainMethodWithDefaultArgument() {
        // Test main method with no arguments (uses default)
        String[] args = {};
        VulnerableCommand.main(args);
        
        String output = outContent.toString();
        assertTrue(output.contains("[Executing]"));
        assertTrue(output.contains("localhost; echo INJECTED"));
    }

    @Test
    public void testMainMethodWithCustomArgument() {
        // Test main method with custom argument
        String[] args = {"192.168.1.1"};
        VulnerableCommand.main(args);
        
        String output = outContent.toString();
        assertTrue(output.contains("[Executing]"));
        assertTrue(output.contains("192.168.1.1"));
    }

    @Test
    public void testMainMethodWithMultipleArguments() {
        // Test main method with multiple arguments (only first is used)
        String[] args = {"example.com", "ignored.com"};
        VulnerableCommand.main(args);
        
        String output = outContent.toString();
        assertTrue(output.contains("[Executing]"));
        assertTrue(output.contains("example.com"));
        assertFalse(output.contains("ignored.com"));
    }

    @Test
    public void testRunSystemCommandOutputFormat() {
        // Test that the output format includes the execution message
        VulnerableCommand.runSystemCommand("localhost");
        
        String output = outContent.toString();
        assertTrue(output.contains("[Executing] ping -c 1 localhost"));
    }

    @Test
    public void testClassInstantiation() {
        // Test that the class can be instantiated (even though methods are static)
        VulnerableCommand command = new VulnerableCommand();
        assertNotNull(command);
    }
}
