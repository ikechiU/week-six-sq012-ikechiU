package com.example.week6_project.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

class TestServletTest extends Mockito {

    @Test
    void doGet() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        TestServlet testServlet = new TestServlet();
        testServlet.doGet(request, response);

        writer.flush();
        assertTrue(stringWriter.toString().contains("Rahman"));
    }

    @Test
    void doPost()  throws Exception{
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("contact")).thenReturn("Ikechi");
        when(request.getParameter("password")).thenReturn("123456");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        TestServlet testServlet = new TestServlet();
        testServlet.doPost(request, response);

        verify(request, atLeast(1)).getParameter("contact");
        writer.flush();
        assertTrue(stringWriter.toString().contains("Ikechi"));
    }
}