package com.vlpa.javamail.main;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FormMailSenderServlet extends  HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>GET request for a FormMailSender Servlet.</h1>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>POST request for a FormMailSender Servlet.</h1>");

        String fromMail = request.getParameter("fromemail");
        String subject = request.getParameter("subject");
        String mailText = request.getParameter("mailtext");

        out.println("<br>fromMail: " + fromMail);
        out.println("<br>subject: " + subject);
        out.println("<br>mailText: " + mailText);

        out.println("</body>");
        out.println("</html>");
    }

}
