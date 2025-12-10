package com.example.servlet;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Basic test for HelloServlet
 */
public class HelloServletTest {

    @Test
    public void testServletInfo() {
        HelloServlet servlet = new HelloServlet();
        assertEquals("Hello Servlet", servlet.getServletInfo());
    }

    @Test
    public void testServletCreation() {
        HelloServlet servlet = new HelloServlet();
        assertNotNull(servlet);
    }
}
