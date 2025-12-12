package com.example.servlet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;

/**
 * Comprehensive test suite for HelloServlet
 */
public class HelloServletTest {

    private HelloServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private StringWriter stringWriter;
    private PrintWriter writer;

    @BeforeEach
    public void setUp() throws Exception {
        servlet = new HelloServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
    }

    @Test
    public void testServletInfo() {
        assertEquals("Hello Servlet", servlet.getServletInfo());
    }

    @Test
    public void testServletCreation() {
        assertNotNull(servlet);
    }

    @Test
    public void testDoGetWithNoParameter() throws Exception {
        when(request.getParameter("name")).thenReturn(null);
        
        servlet.doGet(request, response);
        writer.flush();
        
        String output = stringWriter.toString();
        assertTrue(output.contains("Hello, World!"));
        verify(response).setContentType("text/html;charset=UTF-8");
    }

    @Test
    public void testDoGetWithValidName() throws Exception {
        when(request.getParameter("name")).thenReturn("Alice");
        
        servlet.doGet(request, response);
        writer.flush();
        
        String output = stringWriter.toString();
        assertTrue(output.contains("Hello, Alice!"));
        verify(response).setContentType("text/html;charset=UTF-8");
    }

    @Test
    public void testDoGetWithEmptyName() throws Exception {
        when(request.getParameter("name")).thenReturn("");
        
        servlet.doGet(request, response);
        writer.flush();
        
        String output = stringWriter.toString();
        assertTrue(output.contains("Hello, World!"));
    }

    @Test
    public void testDoGetWithWhitespaceName() throws Exception {
        when(request.getParameter("name")).thenReturn("   ");
        
        servlet.doGet(request, response);
        writer.flush();
        
        String output = stringWriter.toString();
        assertTrue(output.contains("Hello, World!"));
    }

    @Test
    public void testDoGetEscapesScriptTag() throws Exception {
        when(request.getParameter("name")).thenReturn("<script>alert('XSS')</script>");
        
        servlet.doGet(request, response);
        writer.flush();
        
        String output = stringWriter.toString();
        assertFalse(output.contains("<script>"));
        assertTrue(output.contains("&lt;script&gt;"));
        assertTrue(output.contains("&lt;&#x2F;script&gt;"));
    }

    @Test
    public void testDoGetEscapesHtmlTags() throws Exception {
        when(request.getParameter("name")).thenReturn("<b>Bold</b>");
        
        servlet.doGet(request, response);
        writer.flush();
        
        String output = stringWriter.toString();
        assertFalse(output.contains("<b>"));
        assertTrue(output.contains("&lt;b&gt;"));
    }

    @Test
    public void testDoGetEscapesAmpersand() throws Exception {
        when(request.getParameter("name")).thenReturn("Tom & Jerry");
        
        servlet.doGet(request, response);
        writer.flush();
        
        String output = stringWriter.toString();
        assertTrue(output.contains("Tom &amp; Jerry"));
    }

    @Test
    public void testDoGetEscapesQuotes() throws Exception {
        when(request.getParameter("name")).thenReturn("\"quoted\" and 'single'");
        
        servlet.doGet(request, response);
        writer.flush();
        
        String output = stringWriter.toString();
        assertTrue(output.contains("&quot;quoted&quot;"));
        assertTrue(output.contains("&#x27;single&#x27;"));
    }

    @Test
    public void testDoGetEscapesEventHandlers() throws Exception {
        when(request.getParameter("name")).thenReturn("<img src=x onerror=alert('XSS')>");
        
        servlet.doGet(request, response);
        writer.flush();
        
        String output = stringWriter.toString();
        assertFalse(output.contains("<img"));
        assertTrue(output.contains("&lt;img"));
        assertTrue(output.contains("onerror"));
    }

    @Test
    public void testDoGetOutputContainsHtmlStructure() throws Exception {
        when(request.getParameter("name")).thenReturn("TestUser");
        
        servlet.doGet(request, response);
        writer.flush();
        
        String output = stringWriter.toString();
        assertTrue(output.contains("<!DOCTYPE html>"));
        assertTrue(output.contains("<html>"));
        assertTrue(output.contains("<head>"));
        assertTrue(output.contains("<title>Hello Servlet</title>"));
        assertTrue(output.contains("<body>"));
        assertTrue(output.contains("</html>"));
    }

    @Test
    public void testDoGetOutputContainsStyles() throws Exception {
        when(request.getParameter("name")).thenReturn("TestUser");
        
        servlet.doGet(request, response);
        writer.flush();
        
        String output = stringWriter.toString();
        assertTrue(output.contains("<style>"));
        assertTrue(output.contains("font-family: Arial"));
    }

    @Test
    public void testDoGetOutputContainsWelcomeMessage() throws Exception {
        when(request.getParameter("name")).thenReturn("TestUser");
        
        servlet.doGet(request, response);
        writer.flush();
        
        String output = stringWriter.toString();
        assertTrue(output.contains("Welcome to the GHAS Java Demo application"));
    }

    @Test
    public void testDoGetOutputContainsHomeLink() throws Exception {
        when(request.getParameter("name")).thenReturn("TestUser");
        
        servlet.doGet(request, response);
        writer.flush();
        
        String output = stringWriter.toString();
        assertTrue(output.contains("index.jsp"));
        assertTrue(output.contains("Go to Home Page"));
    }

    @Test
    public void testDoPostCallsDoGet() throws Exception {
        when(request.getParameter("name")).thenReturn("PostUser");
        
        servlet.doPost(request, response);
        writer.flush();
        
        String output = stringWriter.toString();
        assertTrue(output.contains("Hello, PostUser!"));
        verify(response).setContentType("text/html;charset=UTF-8");
    }

    @Test
    public void testDoGetEscapesMultipleCharacters() throws Exception {
        when(request.getParameter("name")).thenReturn("<>&\"'/");
        
        servlet.doGet(request, response);
        writer.flush();
        
        String output = stringWriter.toString();
        assertTrue(output.contains("&lt;&gt;&amp;&quot;&#x27;&#x2F;"));
    }
}
